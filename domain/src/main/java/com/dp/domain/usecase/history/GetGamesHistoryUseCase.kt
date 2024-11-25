package com.dp.domain.usecase.history

import com.dp.domain.model.GameSession
import kotlinx.coroutines.flow.Flow

/**
 * Use case interface for retrieving the history of all game sessions.
 *
 * This use case provides the list of game sessions that have been played.
 */
fun interface GetGamesHistoryUseCase {

    /**
     * Retrieves the flow of all game sessions in the history.
     *
     * @return A [Flow] emitting a list of [GameSession] objects representing the game history.
     */
    operator fun invoke(): Flow<List<GameSession>>
}