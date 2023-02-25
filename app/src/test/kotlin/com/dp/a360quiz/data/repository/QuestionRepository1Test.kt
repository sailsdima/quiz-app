package com.dp.a360quiz.data.repository

import io.kotest.assertions.asClue
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class QuestionRepository1Test : FreeSpec() {
    //    beforeTest(KotestDispatcherRule.beforeTest)
//    afterTest(KotestDispatcherRule.afterTest)
    init {
        "Scenario. Single case" - {

            //region Variables
            val expectedCode = "response".length
            //endregion

            "Given server is up" {
//                testEnvironment.start()
            }

            "When request prepared and sent" {
//                val request = Request()
//                tester.send(request)
            }

            lateinit var response: String
            "Then response received" {
                response = "response"
            }

            "And has $expectedCode code" {
                assertSoftly {
                    response.asClue {
                        it.length shouldBe expectedCode
//                        it.body.shouldNotBeBlank()
                    }
                }


//                assertion.message shouldContain "The following 2 assertions failed"

//                log.error("Expected assertion", assertion)
            }
        }
    }
}
