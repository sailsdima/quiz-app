package com.dp.usecase.impl.game

import com.dp.domain.model.GameSession
import com.dp.domain.repository.GameSessionRepository
import com.dp.domain.usecase.game.GetGameSessionUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of [GetGameSessionUseCase].
 *
 * This use case retrieves a specific game session from the repository by its ID.
 *
 * @param gameSessionRepository The repository used to fetch game sessions.
 */
class GetGameSessionUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository
) : GetGameSessionUseCase {

    /**
     * Retrieves a specific game session.
     *
     * @param gameSessionId The ID of the game session to retrieve.
     * @return A flow of the game session.
     */
    override fun invoke(gameSessionId: Long): Flow<GameSession> =
        gameSessionRepository.getGameSession(gameSessionId)
}