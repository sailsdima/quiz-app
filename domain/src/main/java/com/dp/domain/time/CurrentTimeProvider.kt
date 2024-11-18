package com.dp.domain.time

interface CurrentTimeProvider {
    val currentTime: Long
    val elapsedTime: Long
}