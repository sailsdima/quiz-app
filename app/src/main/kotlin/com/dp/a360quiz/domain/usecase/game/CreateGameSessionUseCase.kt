package com.dp.a360quiz.domain.usecase.game

interface CreateGameSessionUseCase {

    suspend fun execute(): Long

}