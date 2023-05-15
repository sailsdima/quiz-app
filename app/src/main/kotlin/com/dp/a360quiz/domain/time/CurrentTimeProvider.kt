package com.dp.a360quiz.domain.time

interface CurrentTimeProvider {
    val currentTime: Long
    val elapsedTime: Long
}