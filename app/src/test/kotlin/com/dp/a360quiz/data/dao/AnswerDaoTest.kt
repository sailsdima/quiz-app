package com.dp.a360quiz.data.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.dp.a360quiz.data.dao.DBModelFactory.getAnswer
import com.dp.a360quiz.data.database.QuizDatabase
import com.dp.a360quiz.data.database.dao.AnswerDao
import com.dp.a360quiz.data.database.model.*
import io.kotest.core.spec.style.AnnotationSpec
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AnswerDaoTest : AnnotationSpec() {

    private lateinit var answerDao: AnswerDao
    private lateinit var database: QuizDatabase

    init {
        beforeEach {
            val context = ApplicationProvider.getApplicationContext<Context>()
            database = Room.inMemoryDatabaseBuilder(context, QuizDatabase::class.java)
                .allowMainThreadQueries()
                .build()

            answerDao = database.answerDao()
        }

        afterEach {
            database.close()
        }
    }


    @Test
    fun `Given answer with not existing question id, When insertAnswer called, Then exception is thrown`() {
        val answer = listOf(getAnswer(1, 1))
        answerDao.insertAnswers(answer)
    }

}