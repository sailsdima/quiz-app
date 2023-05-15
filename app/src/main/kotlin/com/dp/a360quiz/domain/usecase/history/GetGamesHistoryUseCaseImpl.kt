package com.dp.a360quiz.domain.usecase.history

import com.dp.a360quiz.domain.model.GameSession
import com.dp.a360quiz.domain.repository.GameSessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGamesHistoryUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository
) : GetGamesHistoryUseCase {

    override val gameHistory: Flow<List<GameSession>>
        get() = gameSessionRepository.gameSessions
}