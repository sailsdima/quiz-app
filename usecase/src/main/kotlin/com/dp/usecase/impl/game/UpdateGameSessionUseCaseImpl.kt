package com.dp.usecase.impl.game

import com.dp.domain.model.GameStatus
import com.dp.domain.repository.GameSessionRepository
import com.dp.domain.time.CurrentTimeProvider
import com.dp.domain.usecase.game.UpdateGameSessionUseCase
import javax.inject.Inject

class UpdateGameSessionUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository,
    private val currentTimeProvider: CurrentTimeProvider
) : UpdateGameSessionUseCase {

    override suspend fun startGame(gameSessionId: Long) {
        gameSessionRepository.updateGameSession(
            gameSessionId,
            newGameStatus = GameStatus.STARTED,
            newStartedAt = currentTimeProvider.currentTime
        )
    }

    override suspend fun finishGame(gameSessionId: Long, gameDuration: Long) {
        gameSessionRepository.updateGameSession(
            gameSessionId,
            newGameStatus = GameStatus.FINISHED,
            newFinishedAt = currentTimeProvider.currentTime,
            newGameDuration = gameDuration
        )
    }
}