package com.dp.domain.usecase.game

/**
 * Use case to finish a game session.
 */
fun interface FinishGameSessionUseCase {

    /**
     * Finishes a game session by updating its status to "FINISHED".
     *
     * @param gameSessionId The ID of the game session to finish.
     * @param gameDuration The duration of the game in milliseconds.
     */
    suspend operator fun invoke(gameSessionId: Long, gameDuration: Long)
}