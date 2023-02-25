package com.dp.a360quiz.domain.usecase.game

import com.dp.a360quiz.domain.model.GameSession
import kotlinx.coroutines.flow.Flow

interface GetGameSessionUseCase {

    suspend fun execute(gameSessionId: Long): Flow<GameSession>

}