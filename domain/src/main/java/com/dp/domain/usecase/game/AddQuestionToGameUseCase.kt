package com.dp.domain.usecase.game

interface AddQuestionToGameUseCase {

    suspend fun execute(gameSessionId: Long, questionId: Long)

}