package com.dp.usecase.impl.game

import com.dp.domain.model.GameSession
import com.dp.domain.model.NewUserAnswer
import com.dp.domain.model.getCorrectAnswerId
import com.dp.domain.model.getQuestionById
import com.dp.domain.repository.GameSessionRepository
import com.dp.domain.time.CurrentTimeProvider
import com.dp.domain.usecase.game.AddUserAnswerUseCase
import javax.inject.Inject

/**
 * Implementation of [AddUserAnswerUseCase].
 *
 * This use case allows a user to submit an answer for a specific question in an ongoing game session.
 * It ensures the answer is correct or incorrect and records it in the database.
 *
 * @param gameSessionRepository The repository used to store user answers in the database.
 * @param currentTimeProvider A service to retrieve the current time to record when the answer was made.
 */
class AddUserAnswerUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository,
    private val currentTimeProvider: CurrentTimeProvider
) : AddUserAnswerUseCase {

    /**
     * Adds a user's answer for a specific question in a game session.
     *
     * @param gameSession The current game session.
     * @param questionId The ID of the question being answered.
     * @param answerId The ID of the user's selected answer.
     *
     * @throws QuestionNotFoundException if the question is not found.
     */
    override suspend fun invoke(gameSession: GameSession, questionId: Long, answerId: Long) {
        val correctAnswerId = gameSession
            .getQuestionById(questionId)
            ?.getCorrectAnswerId()
            ?: throw Exception("Question with ID $questionId not found") // TODO

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