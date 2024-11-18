package com.dp.domain.usecase.game

interface CreateGameSessionUseCase {

    suspend fun execute(): Long

}