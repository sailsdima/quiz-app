package com.dp.a360quiz.data.dao

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.dp.a360quiz.data.dao.DBModelFactory.getAnswer
import com.dp.a360quiz.data.dao.DBModelFactory.getCategory
import com.dp.a360quiz.data.dao.DBModelFactory.getQuestion
import com.dp.a360quiz.data.database.QuizDatabase
import com.dp.a360quiz.data.database.dao.AnswerDao
import com.dp.a360quiz.data.database.dao.CategoryDao
import com.dp.a360quiz.data.database.dao.QuestionDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class QuestionDaoTest {

    private lateinit var answerDao: AnswerDao
    private lateinit var questionDao: QuestionDao
    private lateinit var categoryDao: CategoryDao
    private lateinit var database: QuizDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, QuizDatabase::class.java
        ).allowMainThreadQueries().build()
        answerDao = database.answerDao()
        categoryDao = database.categoryDao()
        questionDao = database.questionDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun `Given answer with not existing question id, When insertAnswer called, Then exception is thrown`() {
        val answer = listOf(getAnswer(1, 1))
        assertThrows<SQLiteConstraintException> { answerDao.insertAnswers(answer) }
    }

    @Test
    fun `Given answer id that does not exists, When getAnswer called, Then flow returns null`() =
        runTest {
            answerDao.getAnswer(1).test {
                Assert.assertEquals(awaitItem(), null)
            }
        }

    @Test
    fun `Given answer and question inserted in DB, When question is deleted, Then answer is delete too`() =
        runTest {
            val category = getCategory(1)
            val question = getQuestion(1, category.id)
            val answer = getAnswer(1, question.id)
            categoryDao.upsertCategory(category)
            questionDao.upsertQuestion(question)
            answerDao.insertAnswers(listOf(answer))
            questionDao.deleteQuestion(question)

            answerDao.getAnswer(answer.id).test {
                Assert.assertEquals(awaitItem(), null)
            }
        }
}