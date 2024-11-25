package com.dp.domain.timer

import java.time.Duration

/**
 * Constants used by the `QuizTimer` interface and its implementation.
 */
object QuizTimerConstants {
    /**
     * The default finish time for the timer in milliseconds.
     */
    val defaultTimerFinishTime = Duration.ofSeconds(1)

    /**
     * The default step delay for the timer in milliseconds.
     */
    val defaultStepDelay = Duration.ofSeconds(1)
}
