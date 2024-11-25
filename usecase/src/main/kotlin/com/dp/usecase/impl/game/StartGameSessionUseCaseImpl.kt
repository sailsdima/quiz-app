package com.dp.usecase.impl.game

import com.dp.domain.model.GameStatus
import com.dp.domain.repository.GameSessionRepository
import com.dp.domain.time.CurrentTimeProvider
import com.dp.domain.usecase.game.StartGameSessionUseCase
import javax.inject.Inject

/**
 * Implementation of [StartGameSessionUseCase].
 *
 * This use case updates a game session status to "STARTED" and records the start time.
 *
 * @param gameSessionRepository The repository to interact with the game session data.
 * @param currentTimeProvider A service to get the current time for the session start timestamp.
 */
class StartGameSessionUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository,
    private val currentTimeProvider: CurrentTimeProvider
) : StartGameSessionUseCase {

    /**
     * Starts a game session by updating its status to "STARTED".
     *
     * @param gameSessionId The ID of the game session to start.
     */
    override suspend fun invoke(gameSessionId: Long) {
        gameSessionRepository.updateGameSession(
            gameSessionId = gameSessionId,
            newGameStatus = GameStatus.STARTED,
            newStartedAt = currentTimeProvider.currentTime
        )
    }
}