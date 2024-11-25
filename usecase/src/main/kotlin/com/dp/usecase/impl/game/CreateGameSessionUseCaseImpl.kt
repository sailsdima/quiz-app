package com.dp.usecase.impl.game

import com.dp.domain.model.GameStatus
import com.dp.domain.model.NewGameSession
import com.dp.domain.repository.UserDataRepository
import com.dp.domain.repository.GameSessionRepository
import com.dp.domain.time.CurrentTimeProvider
import com.dp.domain.usecase.game.CreateGameSessionUseCase
import javax.inject.Inject

/**
 * Implementation of [CreateGameSessionUseCase].
 *
 * This use case is responsible for creating a new game session with the provided preferences
 * from the app's settings and storing the session in the repository.
 *
 * @param gameSessionRepository The repository to interact with game session data.
 * @param userDataRepository The repository to access the app preferences.
 * @param currentTimeProvider A service to fetch the current time for the creation timestamp.
 */
class CreateGameSessionUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository,
    private val userDataRepository: UserDataRepository,
    private val currentTimeProvider: CurrentTimeProvider
) : CreateGameSessionUseCase {

    /**
     * Creates a new game session.
     *
     * @return The ID of the newly created game session.
     */
    override suspend fun invoke(): Long {
        val gameSession = NewGameSession(
            timePerGameMs = userDataRepository.getTimePerGameMs(),
            questionsCount = userDataRepository.getQuestionsCount(),
            mistakesAllowedCount = userDataRepository.getMistakesAllowedCount(),
            gameStatus = GameStatus.CREATED,
            createdAt = currentTimeProvider.currentTime
        )
        return gameSessionRepository.addGameSession(gameSession = gameSession)
    }
}