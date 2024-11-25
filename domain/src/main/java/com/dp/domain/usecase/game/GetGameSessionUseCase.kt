package com.dp.domain.usecase.game

import com.dp.domain.model.GameSession
import kotlinx.coroutines.flow.Flow

/**
 * Use case to retrieve an existing game session.
 */
fun interface GetGameSessionUseCase {

    /**
     * Retrieves a specific game session.
     *
     * @param gameSessionId The ID of the game session to retrieve.
     * @return A flow of the game session.
     */
    operator fun invoke(gameSessionId: Long): Flow<GameSession>
}