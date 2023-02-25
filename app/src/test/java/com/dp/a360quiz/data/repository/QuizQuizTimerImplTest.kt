package com.dp.a360quiz.data.repository

import com.dp.a360quiz.data.coroutines.DispatcherProvider
import com.dp.a360quiz.domain.time.CurrentTimeProvider
import com.dp.a360quiz.domain.time.QuizTimerImpl
import com.dp.a360quiz.domain.time.TimerState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Test


@ExperimentalCoroutinesApi
class QuizQuizTimerImplTest {

    private val currentTimeProvider = mockk<CurrentTimeProvider>()
    private val dispatcherProvider = mockk<DispatcherProvider>()
    private val quizTimer = QuizTimerImpl(currentTimeProvider, dispatcherProvider)

    private val timerFinishTime = 1000L
    private val stepDelayMs = 100L
    private val timerTimeMs = 2000L

    private val timerStarted = TimerState.Started(kotlin.math.ceil(timerTimeMs / 1000F).toLong())
    private val timerProgress = TimerState.Progress(kotlin.math.ceil((timerTimeMs - stepDelayMs) / 1000F).toLong())
    private val timerPaused = TimerState.Paused
    private val timerFinished = TimerState.Finished

    private val timerFlow = flowOf(timerStarted, timerProgress, timerPaused, timerFinished)

    @Before
    fun setUp() {
        coEvery { currentTimeProvider.elapsedTime } returns 0
        coEvery { dispatcherProvider.io } returns Dispatchers.Unconfined
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `start timer with timerTimeMs more than timerFinishTime`() = runBlockingTest {
        coEvery {
            timerFlow.collect { }
        } coAnswers {
            quizTimer._timerFlow.emit(timerStarted)
            quizTimer._timerFlow.emit(timerProgress)
            quizTimer._timerFlow.emit(timerPaused)
            quizTimer._timerFlow.emit(timerFinished)
        }

        quizTimer.startTimer(timerTimeMs, timerFinishTime, stepDelayMs).collect {
            when (it) {
                is TimerState.Started -> assertEquals(timerStarted.time, it.time)
                is TimerState.Progress -> assertEquals(timerProgress.time, it.time)
                TimerState.Paused -> assertEquals(timerPaused, it)
                TimerState.Finished -> assertEquals(timerFinished, it)
                else -> {

                }
            }
        }
    }
}