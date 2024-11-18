package com.dp.data.repository

import android.database.sqlite.SQLiteConstraintException
import androidx.room.withTransaction
import com.dp.a360quiz.data.database.dao.CategoryDao
import com.dp.a360quiz.data.database.dao.QuestionDao
import com.dp.data.database.QuizDatabase
import com.dp.data.database.converter.DBEntitiesConverter.toDBAnswers
import com.dp.data.database.converter.DBEntitiesConverter.toDBCategory
import com.dp.data.database.converter.DBEntitiesConverter.toDBQuestion
import com.dp.data.database.converter.DBEntitiesConverter.toQuestion
import com.dp.data.database.dao.AnswerDao
import com.dp.data.database.model.DeleteQuestionEntity
import com.dp.domain.model.Question
import com.dp.domain.repository.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val database: QuizDatabase,
    private val answerDao: AnswerDao,
    private val categoryDao: CategoryDao,
    private val questionDao: QuestionDao,
) : QuestionRepository {

    override suspend fun upsertQuestions(questions: List<Question>) {
        database.withTransaction {
            questions.forEach { addQuestion(it) }
        }
    }

    private suspend fun addQuestion(question: Question) {
        val dbCategory = question.category.toDBCategory()
        val categoryId = try {
            categoryDao.upsertCategory(category = dbCategory)
                .takeIf { it >= 0 }
                ?: categoryDao.insertCategory(category = dbCategory)
        } catch (e: SQLiteConstraintException) {
            categoryDao.findCategoryIdByName(name = dbCategory.name)
        }
        val dbQuestion = question.toDBQuestion(categoryId = categoryId)
        questionDao.upsertQuestion(question = dbQuestion)
        val dbAnswers = question.answers.toDBAnswers(questionId = dbQuestion.id)
        answerDao.insertAnswers(answers = dbAnswers)
    }

    override suspend fun getSavedQuestionIds(): List<Long> = questionDao.getQuestionsList()

    override suspend fun deleteQuestions(questionIds: Set<Long>) =
        questionDao.deleteQuestions(questionIds.map { DeleteQuestionEntity(it) }.toTypedArray())

    override suspend fun getQuestionById(questionId: Long): Question =
        questionDao.getQuestionById(questionId).toQuestion()

    override suspend fun getNextRandomQuestion(): Question =
        questionDao.getRandomQuestion().toQuestion()

    override suspend fun incrementQuestionShownTimesCount(questionId: Long) {
        questionDao.incrementQuestionShownTimesCount(questionId)
    }

    override suspend fun clearQuestions() {
        questionDao.clear()
    }
}