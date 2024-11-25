package com.dp.domain.usecase.app_preferences

import kotlinx.coroutines.flow.Flow

/**
 * Use case to retrieve the saved questions version from the repository.
 */
fun interface GetSavedQuestionsVersionUseCase {
    /**
     * Fetches the saved questions version from the repository.
     *
     * @return The version number of the saved questions.
     */
    suspend operator fun invoke(): Int
}

/**
 * Use case to retrieve the user's score from the repository.
 */
fun interface GetUserScoreUseCase {
    /**
     * Fetches the user's score from the repository.
     *
     * @return The user's current score.
     */
    suspend operator fun invoke(): Int
}

/**
 * Use case to retrieve the flow of the user's score from the repository.
 */
fun interface GetUserScoreFlowUseCase {
    /**
     * Fetches the flow of the user's score from the repository.
     *
     * @return A flow representing the user's score.
     */
    operator fun invoke(): Flow<Int>
}

/**
 * Use case to retrieve the time allowed per game in milliseconds from the repository.
 */
fun interface GetTimePerGameMsUseCase {
    /**
     * Fetches the time per game (in milliseconds) from the repository.
     *
     * @return The time per game in milliseconds.
     */
    suspend operator fun invoke(): Long
}

/**
 * Use case to retrieve the total number of questions available for the game.
 */
fun interface GetQuestionsCountUseCase {
    /**
     * Fetches the number of questions available in the repository.
     *
     * @return The total number of questions.
     */
    suspend operator fun invoke(): Int
}

/**
 * Use case to retrieve the allowed number of mistakes in the game.
 */
fun interface GetMistakesAllowedCountUseCase {
    /**
     * Fetches the allowed number of mistakes from the repository.
     *
     * @return The number of allowed mistakes.
     */
    suspend operator fun invoke(): Int
}