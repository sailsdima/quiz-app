package com.dp.domain.usecase.game

import com.dp.domain.model.GameSession


interface AddUserAnswerUseCase {

    /**
     * @throws QuestionNotFoundException
     */
    suspend fun execute(gameSession: GameSession, questionId: Long, answerId: Long)

}