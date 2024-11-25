package com.dp.domain.timer

import com.dp.domain.model.timer.TimerState
import kotlinx.coroutines.flow.Flow
import java.time.Duration

/**
 * Interface representing a timer for a quiz game.
 */
interface QuizTimer {

    /**
     * The total time passed in seconds since the timer started.
     */
    val timePassedSec: Long

    /**
     * Starts the timer with the specified parameters.
     *
     * @param timerTime The initial duration for the timer.
     * @param finishTime The duration at which the timer should finish.
     * Defaults to [QuizTimerConstants.defaultTimerFinishTime].
     * @param stepDelay The delay between timer updates.
     * Defaults to [QuizTimerConstants.defaultStepDelay].
     * @return A flow emitting [TimerState] updates.
     */
    fun startTimer(
        timerTime: Duration,
        finishTime: Duration = QuizTimerConstants.defaultTimerFinishTime,
        stepDelay: Duration = QuizTimerConstants.defaultStepDelay
    ): Flow<TimerState>

    /**
     * Pauses the timer.
     */
    fun pause()

    /**
     * Resumes the timer.
     */
    fun resume()

    /**
     * Resets the timer to its initial state.
     */
    fun reset()
}