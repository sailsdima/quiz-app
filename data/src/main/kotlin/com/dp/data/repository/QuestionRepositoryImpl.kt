package com.dp.data.repository

import android.database.sqlite.SQLiteConstraintException
import com.dp.data.database.dao.CategoryDao
import com.dp.data.database.dao.QuestionDao
import com.dp.data.database.converter.DBEntitiesConverter.toDBAnswers
import com.dp.data.database.converter.DBEntitiesConverter.toDBCategory
import com.dp.data.database.converter.DBEntitiesConverter.toDBQuestion
import com.dp.data.database.converter.DBEntitiesConverter.toQuestion
import com.dp.data.database.dao.AnswerDao
import com.dp.domain.model.Question
import com.dp.domain.repository.QuestionRepository
import javax.inject.Inject

/**
 * Repository implementation for managing question data.
 * Provides methods to interact with the local database for CRUD operations on questions.
 */
class QuestionRepositoryImpl @Inject constructor(
    private val answerDao: AnswerDao,
    private val categoryDao: CategoryDao,
    private val questionDao: QuestionDao,
) : QuestionRepository {

    /**
     * Upserts a list of questions into the database.
     * If the questions already exist, they are updated; otherwise, they are inserted.
     *
     * @param questions The list of questions to upsert.
     */
    override suspend fun upsertQuestions(questions: List<Question>) {
        questions.forEach { addQuestion(question = it) }
    }

    /**
     * Adds a single question to the database. This method handles inserting
     * the category, the question itself, and its associated answers.
     *
     * @param question The question to add to the database.
     */
    private suspend fun addQuestion(question: Question) {
        // Insert or update the category
        val dbCategory = question.category.toDBCategory()
        val categoryId = try {
            categoryDao.upsertCategory(category = dbCategory)
                .takeIf { it >= 0 }
                ?: categoryDao.insertCategory(category = dbCategory)
        } catch (e: SQLiteConstraintException) {
            categoryDao.findCategoryIdByName(name = dbCategory.name)
        }

        // Insert or update the question
        val dbQuestion = question.toDBQuestion(categoryId = categoryId)
        questionDao.upsertQuestion(question = dbQuestion)

        // Insert answers associated with the question
        val dbAnswers = question.answers.toDBAnswers(questionId = dbQuestion.id)
        answerDao.insertAnswers(answers = dbAnswers)
    }

    /**
     * Fetches the list of saved question IDs from the database.
     * This can be used to determine which questions are already saved in the local database.
     *
     * @return A list of question IDs from the local database.
     */
    override suspend fun getSavedQuestionIds(): List<Long> = questionDao.getQuestionsList()

    /**
     * Deletes a set of questions from the local database.
     * This will remove questions by their IDs.
     *
     * @param questionIds A set of question IDs to be deleted from the database.
     */
    override suspend fun deleteQuestions(questionIds: Set<Long>) {
        val chunkSize = 999  // Max number of IDs SQLite can handle in a single query.
        questionIds.chunked(chunkSize).forEach { idsChunk ->
            questionDao.deleteQuestionsByIds(questionIds = idsChunk)
        }
    }

    /**
     * Fetches a specific question by its ID.
     * This method retrieves the question and its associated data from the local database.
     *
     * @param questionId The ID of the question to fetch.
     * @return The question corresponding to the provided ID.
     */
    override suspend fun getQuestionById(questionId: Long): Question {
        val dbQuestion = questionDao.getQuestionById(questionId = questionId)
        return dbQuestion.toQuestion()
    }

    /**
     * Fetches a random question from the local database.
     * This method selects a random question from the set of saved questions.
     *
     * @return A random question from the database.
     */
    override suspend fun getNextRandomQuestion(): Question {
        val dbQuestion = questionDao.getRandomQuestion()
        return dbQuestion.toQuestion()
    }

    /**
     * Increments the count of how many times a question has been shown.
     * This method updates the question's "shown count" in the database.
     *
     * @param questionId The ID of the question for which the shown count should be incremented.
     */
    override suspend fun incrementQuestionShownTimesCount(questionId: Long) {
        questionDao.incrementShownCount(questionId = questionId)
    }

    /**
     * Clears all saved questions from the local database.
     * This method removes all questions and their associated data (answers, etc.) from the database.
     */
    override suspend fun clearQuestions() {
        questionDao.clear()
    }
}