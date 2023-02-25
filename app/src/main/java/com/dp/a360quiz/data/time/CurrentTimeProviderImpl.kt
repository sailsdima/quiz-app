package com.dp.a360quiz.data.time

import android.os.SystemClock
import com.dp.a360quiz.domain.time.CurrentTimeProvider
import javax.inject.Inject

class CurrentTimeProviderImpl @Inject constructor() : CurrentTimeProvider {
    override val currentTime: Long
        get() = System.currentTimeMillis()

    override val elapsedTime: Long
        get() = SystemClock.elapsedRealtime()
}