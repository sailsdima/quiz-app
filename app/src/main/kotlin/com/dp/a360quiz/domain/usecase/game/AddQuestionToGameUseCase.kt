package com.dp.a360quiz.domain.usecase.game

interface AddQuestionToGameUseCase {

    suspend fun execute(gameSessionId: Long, questionId: Long)

}