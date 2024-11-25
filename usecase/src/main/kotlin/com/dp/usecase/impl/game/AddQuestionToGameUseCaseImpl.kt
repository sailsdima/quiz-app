package com.dp.usecase.impl.game

import com.dp.domain.repository.GameSessionRepository
import com.dp.domain.usecase.game.AddQuestionToGameUseCase
import javax.inject.Inject

/**
 * Implementation of [AddQuestionToGameUseCase].
 *
 * This use case adds a question to a game session by calling the appropriate method in the
 * repository to associate the question with the given game session.
 *
 * @param gameSessionRepository The repository used to interact with game sessions in the database.
 */
class AddQuestionToGameUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository
) : AddQuestionToGameUseCase {

    /**
     * Adds a question to a specified game session.
     *
     * @param gameSessionId The ID of the game session.
     * @param questionId The ID of the question to add.
     */
    override suspend fun invoke(gameSessionId: Long, questionId: Long) =
        gameSessionRepository.addQuestionToGame(
            gameSessionId = gameSessionId,
            questionId = questionId
        )
}