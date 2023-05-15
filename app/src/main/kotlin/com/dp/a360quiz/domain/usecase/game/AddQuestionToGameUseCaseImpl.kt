package com.dp.a360quiz.domain.usecase.game

import com.dp.a360quiz.domain.repository.GameSessionRepository
import javax.inject.Inject

class AddQuestionToGameUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository
) : AddQuestionToGameUseCase {

    override suspend fun execute(gameSessionId: Long, questionId: Long) =
        gameSessionRepository.addQuestionToGame(gameSessionId, questionId)
}