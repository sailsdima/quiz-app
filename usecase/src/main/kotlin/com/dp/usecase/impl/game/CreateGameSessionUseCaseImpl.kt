package com.dp.usecase.impl.game

import com.dp.domain.model.GameStatus
import com.dp.domain.model.NewGameSession
import com.dp.domain.repository.AppPreferencesRepository
import com.dp.domain.repository.GameSessionRepository
import com.dp.domain.time.CurrentTimeProvider
import com.dp.domain.usecase.game.CreateGameSessionUseCase
import javax.inject.Inject

class CreateGameSessionUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository,
    private val appPreferencesRepository: AppPreferencesRepository,
    private val currentTimeProvider: CurrentTimeProvider
) : CreateGameSessionUseCase {

    override suspend fun execute(): Long {
        val gameSession = NewGameSession(
            timePerGameMs = appPreferencesRepository.getTimePerGameMs(),
            questionsCount = appPreferencesRepository.getQuestionsCount(),
            mistakesAllowedCount = appPreferencesRepository.getMistakesAllowedCount(),
            gameStatus = GameStatus.CREATED,
            createdAt = currentTimeProvider.currentTime
        )
        return gameSessionRepository.addGameSession(gameSession)
    }

}