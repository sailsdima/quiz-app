package com.dp.a360quiz.domain.time

import com.dp.a360quiz.di.module.AppCoroutineScope
import com.dp.a360quiz.extensions.logd
import com.dp.a360quiz.extensions.loge
import com.dp.a360quiz.extensions.logi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject
import kotlin.math.ceil

class QuizTimerImpl @Inject constructor(
    private val currentTimeProvider: CurrentTimeProvider,
    @AppCoroutineScope private val coroutineScope: CoroutineScope,
) : QuizTimer {

    private var timerJob: Job? = null

    private val _timerFlow = MutableSharedFlow<TimerState>()

    private var timerFinishTime = DEFAULT_TIMER_FINISH_TIME
    private var stepDelayMs = DEFAULT_STEP_DELAY_MS

    private val isPaused = AtomicBoolean(false)
    private val lastTimerTime = AtomicReference<Long?>(null)
    private val startedAt = AtomicReference<Long?>(null)
    private val timeLeftMs = AtomicReference<Long?>(null)
    private val _timePassed = AtomicLong(0)

    override val timePassedSec: Long
        get() = _timePassed.get() / MS_IN_SEC

    override fun startTimer(timerTimeMs: Long, finishTime: Long, stepDelayMs: Long): Flow<TimerState> {
        _timePassed.set(0)
        this.timerFinishTime = finishTime
        this.stepDelayMs = stepDelayMs

        startTimerInternal(timerTimeMs)
        return _timerFlow
    }

    private fun startTimerInternal(timerTimeMs: Long) {
        if (isPaused.get() || timerTimeMs < timerFinishTime) {
            loge("Can not start timer as given time $timerTimeMs is less then $timerFinishTime OR timer is already running (isPaused: $isPaused)")
            return
        }
        cancelTimer()

        val initialDelay = timerTimeMs % stepDelayMs

        lastTimerTime.set(timerTimeMs)
        startedAt.set(currentTimeProvider.elapsedTime)
        val timerInterval = (timerTimeMs - initialDelay downTo timerFinishTime step stepDelayMs)
        timerJob = timerInterval.asFlow()
            .onStart {
                postTimerStarted(timeLeftMs = timerTimeMs)
                delay(initialDelay)
                _timePassed.addAndGet(initialDelay)
            }
            .onEach { timeLeftMs ->
                postTimerProgress(timeLeftMs)
                delay(stepDelayMs)
                _timePassed.addAndGet(stepDelayMs)
                logi("onEach $timeLeftMs")
            }
            .onCompletion { error ->
                logi("onCompletion $error")
                error?.let {
                    postTimerPaused(timeLeftMs.get())
                } ?: run {
                    postTimerFinished(timeLeftMs.get())
                    reset()
                }
            }
            .launchIn(coroutineScope)
    }

    override fun pause() {
        if (isPaused.get()) {
            logi("Timer is already paused")
            return
        }

        cancelTimer()
        isPaused.set(true)

        val startedAt = startedAt.get() ?: return
        val lastTimerTime = lastTimerTime.get() ?: return

        val pausedAt = currentTimeProvider.elapsedTime
        timeLeftMs.set(lastTimerTime - (pausedAt - startedAt))

        logd("Timer pause. timeLeftMs = $timeLeftMs.")
    }

    override fun resume() {
        if (!isPaused.get()) return
        val timeLeftMs = timeLeftMs.get() ?: return

        logi("Timer resume. Remaining time $timeLeftMs")
        isPaused.set(false)
        startTimerInternal(timeLeftMs)
    }

    override fun reset() {
        logi("Timer reset")
        cancelTimer()
        startedAt.set(null)
        lastTimerTime.set(null)
        isPaused.set(false)
    }

    private suspend fun postTimerStarted(timeLeftMs: Long) {
        _timerFlow.emit(TimerState.Started(ceil(timeLeftMs / MS_IN_SEC.toFloat()).toLong()))
    }

    private suspend fun postTimerProgress(timeLeftMs: Long) {
        _timerFlow.emit(TimerState.Progress(ceil(timeLeftMs / MS_IN_SEC.toFloat()).toLong()))
    }

    private suspend fun postTimerPaused(timeLeftMs: Long?) {
        _timerFlow.emit(TimerState.Paused(timeLeftMs ?: 0))
    }

    private suspend fun postTimerFinished(timeLeftMs: Long?) {
        _timerFlow.emit(TimerState.Finished(timeLeftMs ?: 0))
    }

    private fun cancelTimer() {
        timerJob?.cancel(CancellationException("Timer has been cancelled"))
    }

}