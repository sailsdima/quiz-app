package com.dp.a360quiz.domain.usecase.game

import com.dp.a360quiz.common.exception.QuestionNotFoundException
import com.dp.a360quiz.domain.model.GameSession
import com.dp.a360quiz.domain.model.NewUserAnswer
import com.dp.a360quiz.domain.model.getCorrectAnswerId
import com.dp.a360quiz.domain.model.getQuestionById
import com.dp.a360quiz.domain.repository.GameSessionRepository
import com.dp.a360quiz.domain.time.CurrentTimeProvider
import javax.inject.Inject

class AddUserAnswerUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository,
    private val currentTimeProvider: CurrentTimeProvider
) : AddUserAnswerUseCase {

    override suspend fun execute(gameSession: GameSession, questionId: Long, answerId: Long) {
        val correctAnswerId = gameSession
            .getQuestionById(questionId)
            ?.getCorrectAnswerId()
            ?: throw QuestionNotFoundException()

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