package com.dp.usecase.impl.history

import com.dp.domain.model.GameSession
import com.dp.domain.repository.GameSessionRepository
import com.dp.domain.usecase.history.GetGamesHistoryUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of [GetGamesHistoryUseCase].
 *
 * This use case implementation retrieves the game history by fetching all game sessions
 * stored in the repository and emitting them as a flow.
 *
 * @param gameSessionRepository The repository used to fetch game session data.
 */
class GetGamesHistoryUseCaseImpl @Inject constructor(
    private val gameSessionRepository: GameSessionRepository
) : GetGamesHistoryUseCase {

    /**
     * Invokes the use case to retrieve all game sessions.
     *
     * @return A flow that emits a list of [GameSession] objects representing the game history.
     */
    override fun invoke(): Flow<List<GameSession>> = gameSessionRepository.gameSessions
}