package com.dp.a360quiz.domain.usecase.game

import com.dp.a360quiz.domain.model.GameSession
import com.dp.a360quiz.domain.repository.GameSessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameSessionUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository
) : GetGameSessionUseCase {

    override suspend fun execute(gameSessionId: Long): Flow<GameSession> =
        gameSessionRepository.getGameSession(gameSessionId)
}