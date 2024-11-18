package com.dp.usecase.impl.history

import com.dp.domain.model.GameSession
import com.dp.domain.repository.GameSessionRepository
import com.dp.domain.usecase.history.GetGamesHistoryUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGamesHistoryUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository
) : GetGamesHistoryUseCase {

    override val gameHistory: Flow<List<GameSession>>
        get() = gameSessionRepository.gameSessions
}