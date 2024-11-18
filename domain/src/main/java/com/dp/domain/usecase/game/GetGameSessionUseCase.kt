package com.dp.domain.usecase.game

import com.dp.domain.model.GameSession
import kotlinx.coroutines.flow.Flow

interface GetGameSessionUseCase {

    fun execute(gameSessionId: Long): Flow<GameSession>

}