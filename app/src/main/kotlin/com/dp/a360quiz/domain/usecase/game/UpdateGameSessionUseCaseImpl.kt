package com.dp.a360quiz.domain.usecase.game

import com.dp.a360quiz.domain.model.GameStatus
import com.dp.a360quiz.domain.repository.GameSessionRepository
import com.dp.a360quiz.domain.time.CurrentTimeProvider
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