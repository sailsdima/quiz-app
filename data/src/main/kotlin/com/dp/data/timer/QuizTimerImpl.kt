package com.dp.data.timer

import com.dp.core.di.AppCoroutineScope
import com.dp.domain.model.timer.TimerState
import com.dp.domain.time.CurrentTimeProvider
import com.dp.domain.timer.QuizTimer
import com.dp.domain.timer.QuizTimerConstants
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import java.time.Duration
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

private const val DEFAULT_TIME_PASSED = 0L
private const val SECONDS_IN_MILLIS = 1000L

/**
 * Implementation of [QuizTimer] that provides a timer with start, pause, resume, and reset functionalities.
 * The timer emits updates on its state via a [Flow].
 *
 * @property currentTimeProvider Provides the current time for timer calculations.
 * @property coroutineScope Scope in which timer-related coroutines are launched.
 */
class QuizTimerImpl @Inject constructor(
    private val currentTimeProvider: CurrentTimeProvider,
    @AppCoroutineScope private val coroutineScope: CoroutineScope,
) : QuizTimer {

    private var timerJob: Job? = null

    private val _timerFlow = MutableSharedFlow<TimerState>()

    private var timerFinishTime = QuizTimerConstants.defaultTimerFinishTime
    private var stepDelay = QuizTimerConstants.defaultStepDelay

    private val lastTimerTime = AtomicReference<Duration?>(null)
    private val timeLeft = AtomicReference<Duration?>(null)

    private val isPaused = AtomicBoolean(false)
    private val startedAt = AtomicReference<Long?>(null)
    private val _timePassed = AtomicLong(DEFAULT_TIME_PASSED)

    /**
     * The total time passed in seconds since the timer started.
     */
    override val timePassedSec: Long
        get() = _timePassed.get() / SECONDS_IN_MILLIS

    /**
     * Starts the timer with the specified parameters.
     *
     * @param timerTime The initial duration for the timer.
     * @param finishTime The duration at which the timer should finish.
     * @param stepDelay The delay between timer updates.
     * @return A flow emitting [TimerState] updates.
     */
    override fun startTimer(
        timerTime: Duration,
        finishTime: Duration,
        stepDelay: Duration,
    ): Flow<TimerState> {
        _timePassed.set(0)
        this.timerFinishTime = finishTime
        this.stepDelay = stepDelay

        startTimerInternal(timerTime = timerTime)
        return _timerFlow
    }

    /**
     * Initializes and starts the timer's internal state.
     *
     * @param timerTime The total duration of the timer.
     */
    private fun startTimerInternal(timerTime: Duration) {
        if (isPaused.get() || timerTime < timerFinishTime) {
            Timber.d("Cannot start timer as given time $timerTime is less than $timerFinishTime OR timer is already running (isPaused: $isPaused)")
            return
        }
        cancelTimer()

        val initialDelay = Duration.ofMillis(timerTime.toMillis() % stepDelay.toMillis())
        lastTimerTime.set(timerTime)
        startedAt.set(currentTimeProvider.elapsedTime)

        val timerInterval = generateSequence(seed = timerTime) { it.minus(stepDelay) }
            .takeWhile { it >= timerFinishTime }

        timerJob = timerInterval.asFlow()
            .onStart { initializeTimer(initialDelay = initialDelay, timerTime = timerTime) }
            .onEach { tick(timeLeft = it) }
            .onCompletion { handleCompletion(cause = it) }
            .launchIn(coroutineScope)
    }

    /**
     * Initializes the timer by emitting the started state and applying the initial delay.
     *
     * @param initialDelay The initial delay before the timer starts ticking.
     * @param timerTime The total duration of the timer.
     */
    private suspend fun initializeTimer(initialDelay: Duration, timerTime: Duration) {
        postTimerStarted(timeLeft = timerTime)
        delay(timeMillis = initialDelay.toMillis())
        _timePassed.addAndGet(initialDelay.toMillis())
    }

    /**
     * Emits a timer progress update and applies the step delay.
     *
     * @param timeLeft The remaining duration for the timer.
     */
    private suspend fun tick(timeLeft: Duration) {
        postTimerProgress(timeLeft = timeLeft)
        delay(timeMillis = stepDelay.toMillis())
        _timePassed.addAndGet(stepDelay.toMillis())
    }

    /**
     * Handles the completion of the timer, emitting the appropriate state and resetting.
     *
     * @param cause The cause of the timer completion, if any.
     */
    private suspend fun handleCompletion(cause: Throwable?) {
        cause?.let {
            postTimerPaused(timeLeft.get())
        } ?: postTimerFinished(timeLeft.get())
        reset()
    }

    /**
     * Pauses the timer and calculates the remaining time.
     */
    override fun pause() {
        if (isPaused.get()) {
            Timber.d("Timer is already paused")
            return
        }

        cancelTimer()
        isPaused.set(true)

        val startedAt = startedAt.get() ?: return
        val lastTimerTime = lastTimerTime.get() ?: return

        val pausedAt = currentTimeProvider.elapsedTime
        val elapsedSinceStart = Duration.ofMillis(pausedAt - startedAt)

        timeLeft.set(lastTimerTime.minus(elapsedSinceStart))

        Timber.d("Timer paused. Time left: ${timeLeft.get()}")
    }

    /**
     * Resumes the timer from its paused state.
     */
    override fun resume() {
        if (!isPaused.get()) {
            Timber.d("Cannot resume as the timer is not paused")
            return
        }

        val remainingTime = timeLeft.get() ?: return
        Timber.d("Resuming timer with remaining time: $remainingTime")

        isPaused.set(false)
        startTimerInternal(timerTime = remainingTime)
    }

    /**
     * Resets the timer to its initial state.
     */
    override fun reset() {
        Timber.d("Resetting timer")
        cancelTimer()
        startedAt.set(null)
        lastTimerTime.set(null)
        timeLeft.set(null)
        isPaused.set(false)
        _timePassed.set(0)
    }

    /**
     * Emits the started state of the timer.
     *
     * @param timeLeft The remaining duration for the timer.
     */
    private suspend fun postTimerStarted(timeLeft: Duration) {
        _timerFlow.emit(TimerState.Started(time = timeLeft.seconds))
    }

    /**
     * Emits the progress state of the timer.
     *
     * @param timeLeft The remaining duration for the timer.
     */
    private suspend fun postTimerProgress(timeLeft: Duration) {
        _timerFlow.emit(TimerState.Progress(time = timeLeft.seconds))
    }

    /**
     * Emits the paused state of the timer.
     *
     * @param timeLeft The remaining duration for the timer, if available.
     */
    private suspend fun postTimerPaused(timeLeft: Duration?) {
        _timerFlow.emit(TimerState.Paused(time = timeLeft?.seconds ?: 0))
    }

    /**
     * Emits the finished state of the timer.
     *
     * @param timeLeft The remaining duration for the timer, if available.
     */
    private suspend fun postTimerFinished(timeLeft: Duration?) {
        _timerFlow.emit(TimerState.Finished(time = timeLeft?.seconds ?: 0))
    }

    /**
     * Cancels the current timer job.
     */
    private fun cancelTimer() {
        timerJob?.cancel(CancellationException("Timer has been cancelled"))
    }
}