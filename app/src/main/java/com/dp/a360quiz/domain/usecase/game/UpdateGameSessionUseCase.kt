package com.dp.a360quiz.domain.usecase.game

interface UpdateGameSessionUseCase {

    suspend fun startGame(gameSessionId: Long)

    suspend fun finishGame(gameSessionId: Long, gameDuration: Long)

}