package com.dp.a360quiz.domain.usecase.game

import com.dp.a360quiz.domain.model.GameStatus
import com.dp.a360quiz.domain.model.NewGameSession
import com.dp.a360quiz.domain.repository.AppPreferencesRepository
import com.dp.a360quiz.domain.repository.GameSessionRepository
import com.dp.a360quiz.domain.time.CurrentTimeProvider
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