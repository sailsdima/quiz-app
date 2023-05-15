package com.dp.a360quiz.domain.time

import kotlinx.coroutines.flow.Flow

const val DEFAULT_TIMER_FINISH_TIME = 1000L
const val DEFAULT_STEP_DELAY_MS = 1000L
const val MS_IN_SEC = 1000L

interface QuizTimer {

    val timePassedSec: Long

    fun startTimer(
        timerTimeMs: Long,
        finishTime: Long = DEFAULT_TIMER_FINISH_TIME,
        stepDelayMs: Long = DEFAULT_STEP_DELAY_MS
    ): Flow<TimerState>

    fun pause()

    fun resume()

    fun reset()

}