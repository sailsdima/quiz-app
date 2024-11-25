package com.dp.domain.usecase.game

import com.dp.domain.model.GameSession

/**
 * Use case to add a user's answer to a game session.
 */
fun interface AddUserAnswerUseCase {

    /**
     * Adds a user's answer for a specific question in a game session.
     *
     * @param gameSession The current game session.
     * @param questionId The ID of the question being answered.
     * @param answerId The ID of the user's selected answer.
     *
     * @throws QuestionNotFoundException if the question is not found.
     */
    suspend operator fun invoke(gameSession: GameSession, questionId: Long, answerId: Long)
}