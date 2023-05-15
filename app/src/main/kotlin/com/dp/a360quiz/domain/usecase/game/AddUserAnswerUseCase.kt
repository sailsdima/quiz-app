package com.dp.a360quiz.domain.usecase.game

import com.dp.a360quiz.common.exception.QuestionNotFoundException
import com.dp.a360quiz.domain.model.GameSession

interface AddUserAnswerUseCase {

    /**
     * @throws QuestionNotFoundException
     */
    suspend fun execute(gameSession: GameSession, questionId: Long, answerId: Long)

}