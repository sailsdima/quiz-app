package com.dp.domain.usecase.game

/**
 * Use case to start a game session.
 */
fun interface StartGameSessionUseCase {

    /**
     * Starts a game session by updating its status to "STARTED".
     *
     * @param gameSessionId The ID of the game session to start.
     */
    suspend operator fun invoke(gameSessionId: Long)
}