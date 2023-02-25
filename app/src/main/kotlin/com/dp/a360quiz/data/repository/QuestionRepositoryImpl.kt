package com.dp.a360quiz.data.repository

import android.database.sqlite.SQLiteConstraintException
import com.dp.a360quiz.data.converters.DBEntitiesConverter.toDBAnswers
import com.dp.a360quiz.data.converters.DBEntitiesConverter.toDBCategory
import com.dp.a360quiz.data.converters.DBEntitiesConverter.toDBQuestion
import com.dp.a360quiz.data.converters.DBEntitiesConverter.toQuestion
import com.dp.a360quiz.data.database.dao.AnswerDao
import com.dp.a360quiz.data.database.dao.CategoryDao
import com.dp.a360quiz.data.database.dao.QuestionDao
import com.dp.a360quiz.data.database.model.DeleteQuestionEntity
import com.dp.a360quiz.di.module.IoDispatcher
import com.dp.a360quiz.domain.model.Question
import com.dp.a360quiz.domain.repository.QuestionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val answerDao: AnswerDao,
    private val categoryDao: CategoryDao,
    private val questionDao: QuestionDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : QuestionRepository {

    override suspend fun upsertQuestions(questions: List<Question>): Unit = withContext(ioDispatcher) {
        questions.forEach { addQuestion(it) }
    }

    private fun addQuestion(question: Question) {
        val dbCategory = question.category.toDBCategory()
        val categoryId = try {
            categoryDao.upsertCategory(dbCategory)
        } catch (e: SQLiteConstraintException) {
            categoryDao.findCategoryIdByName(dbCategory.name)
        }
        val dbQuestion = question.toDBQuestion(categoryId)
        questionDao.upsertQuestion(dbQuestion)
        val dbAnswers = question.answers.toDBAnswers(dbQuestion.id)
        answerDao.insertAnswers(dbAnswers)
    }

    override suspend fun getSavedQuestionIds(): List<Long> = withContext(ioDispatcher) {
        return@withContext questionDao.getQuestionsList()
    }

    override suspend fun deleteQuestions(questionIds: Set<Long>) = withContext(ioDispatcher) {
        questionDao.deleteQuestions(questionIds.map { DeleteQuestionEntity(it) }.toTypedArray())
    }

    override suspend fun getQuestionById(questionId: Long): Question {
        return questionDao.getQuestionById(questionId).toQuestion()
    }

    override suspend fun getNextRandomQuestion(): Question = withContext(ioDispatcher) {
        return@withContext questionDao.getRandomQuestion().toQuestion()
    }

    override suspend fun incrementQuestionShownTimesCount(questionId: Long): Unit = withContext(ioDispatcher) {
        questionDao.incrementQuestionShownTimesCount(questionId)
    }

    override suspend fun clearQuestions() = withContext(ioDispatcher) {
        questionDao.clear()
    }
}