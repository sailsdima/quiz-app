package com.dp.a360quiz.domain.time

sealed class TimerState(val time: Long) {
    class Started(time: Long) : TimerState(time)
    class Progress(time: Long) : TimerState(time)
    class Paused(time: Long) : TimerState(time)
    class Finished(time: Long) : TimerState(time)
}