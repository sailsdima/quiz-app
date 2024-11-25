package com.dp.a360quiz.data.repository

import com.dp.a360quiz.KotestDispatcherRule
import com.dp.a360quiz.data.coroutines.DispatcherProvider
import com.dp.a360quiz.data.database.QuizDatabase
import com.dp.a360quiz.data.database.dao.AnswerDao
import com.dp.data.database.dao.CategoryDao
import com.dp.data.database.dao.QuestionDao
import com.dp.a360quiz.domain.repository.QuestionRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldBeIn
import io.mockk.Called
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class QuestionRepositoryTest : BehaviorSpec() {
    //    beforeTest(KotestDispatcherRule.beforeTest)
//    afterTest(KotestDispatcherRule.afterTest)
    init {
        coroutineTestScope = true

        val database: QuizDatabase = mockk()
        val answerDao: AnswerDao = mockk()
        val categoryDao: CategoryDao = mockk()
        val questionDao: QuestionDao = mockk()
        lateinit var questionRepository: QuestionRepository

        beforeTest {
            questionRepository =
                QuestionRepositoryImpl(database, answerDao, categoryDao, questionDao, object : DispatcherProvider{
                    override val io: CoroutineDispatcher
                        get() = KotestDispatcherRule.testDispatcher
                    override val default: CoroutineDispatcher
                        get() = KotestDispatcherRule.testDispatcher
                    override val main: CoroutineDispatcher
                        get() = KotestDispatcherRule.testDispatcher
                })
        }
        given("a broomstick") {
            `when`("I sit on it") {
                then("I should be able to fly") {
                    // test code
                }
            }
            `when`("I throw it away") {
                then("it should come back") {
                    // test code
                }
            }
        }

        "" shouldBeIn arrayOf("", "asdf")

        given("list empty list of questions") {
            When("addQuestions called") {
                then("no actions with database happens") {
                    questionRepository.upsertQuestions(emptyList())
                    verify {
                        answerDao wasNot Called
                        categoryDao wasNot Called
                        questionDao wasNot Called
                    }
                }
            }

        }
    }


}
