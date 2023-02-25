@file:OptIn(ExperimentalCoroutinesApi::class)

package com.dp.a360quiz

import io.kotest.core.spec.AfterTest
import io.kotest.core.spec.BeforeTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

object KotestDispatcherRule {
    val testDispatcher = UnconfinedTestDispatcher()

    @ExperimentalCoroutinesApi
    val beforeTest: BeforeTest = {
        Dispatchers.setMain(testDispatcher)
    }

    val afterTest: AfterTest = {
        Dispatchers.resetMain()
    }
}