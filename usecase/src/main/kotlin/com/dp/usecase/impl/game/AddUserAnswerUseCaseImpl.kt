package com.dp.usecase.impl.game

import com.dp.domain.model.GameSession
import com.dp.domain.model.NewUserAnswer
import com.dp.domain.model.getCorrectAnswerId
import com.dp.domain.model.getQuestionById
import com.dp.domain.repository.GameSessionRepository
import com.dp.domain.time.CurrentTimeProvider
import com.dp.domain.usecase.game.AddUserAnswerUseCase
import javax.inject.Inject

class AddUserAnswerUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository,
    private val currentTimeProvider: CurrentTimeProvider
) : AddUserAnswerUseCase {

    override suspend fun execute(gameSession: GameSession, questionId: Long, answerId: Long) {
        val correctAnswerId = gameSession
            .getQuestionById(questionId)
            ?.getCorrectAnswerId()
            ?: throw Exception() //TODO QuestionNotFoundException()

        val isUserAnswerCorrect = answerId == correctAnswerId

        val answer = NewUserAnswer(
            gameId = gameSession.id,
            questionId = questionId,
            answerId = answerId,
            answeredAt = currentTimeProvider.currentTime,
            isCorrect = isUserAnswerCorrect
        )
        gameSessionRepository.addUserAnswer(answer)
    }
}