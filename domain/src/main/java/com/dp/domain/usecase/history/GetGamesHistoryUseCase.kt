package com.dp.domain.usecase.history

import com.dp.domain.model.GameSession
import kotlinx.coroutines.flow.Flow

interface GetGamesHistoryUseCase {
    val gameHistory: Flow<List<GameSession>>
}