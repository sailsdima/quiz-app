package com.dp.usecase.impl.game

import com.dp.domain.repository.GameSessionRepository
import com.dp.domain.usecase.game.AddQuestionToGameUseCase
import javax.inject.Inject

class AddQuestionToGameUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository
) : AddQuestionToGameUseCase {

    override suspend fun execute(gameSessionId: Long, questionId: Long) =
        gameSessionRepository.addQuestionToGame(gameSessionId, questionId)
}