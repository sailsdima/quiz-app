package com.dp.a360quiz.domain.usecase.history

import com.dp.a360quiz.domain.model.GameSession
import kotlinx.coroutines.flow.Flow

interface GetGamesHistoryUseCase {
    val gameHistory: Flow<List<GameSession>>
}