package com.dp.usecase.impl.game

import com.dp.domain.model.GameSession
import com.dp.domain.repository.GameSessionRepository
import com.dp.domain.usecase.game.GetGameSessionUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameSessionUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository
) : GetGameSessionUseCase {

    override fun execute(gameSessionId: Long): Flow<GameSession> =
        gameSessionRepository.getGameSession(gameSessionId)
}