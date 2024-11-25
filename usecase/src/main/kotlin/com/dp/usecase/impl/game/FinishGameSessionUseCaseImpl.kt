package com.dp.usecase.impl.game

import com.dp.domain.model.GameStatus
import com.dp.domain.repository.GameSessionRepository
import com.dp.domain.time.CurrentTimeProvider
import com.dp.domain.usecase.game.FinishGameSessionUseCase
import javax.inject.Inject

/**
 * Implementation of [FinishGameSessionUseCase].
 *
 * This use case updates a game session status to "FINISHED" and records the finish time
 * and game duration.
 *
 * @param gameSessionRepository The repository to interact with the game session data.
 * @param currentTimeProvider A service to get the current time for the session finish timestamp.
 */
class FinishGameSessionUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository,
    private val currentTimeProvider: CurrentTimeProvider
) : FinishGameSessionUseCase {

    /**
     * Finishes a game session by updating its status to "FINISHED".
     *
     * @param gameSessionId The ID of the game session to finish.
     * @param gameDuration The duration of the game in milliseconds.
     */
    override suspend fun invoke(gameSessionId: Long, gameDuration: Long) {
        gameSessionRepository.updateGameSession(
            gameSessionId = gameSessionId,
            newGameStatus = GameStatus.FINISHED,
            newFinishedAt = currentTimeProvider.currentTime,
            newGameDuration = gameDuration
        )
    }
}