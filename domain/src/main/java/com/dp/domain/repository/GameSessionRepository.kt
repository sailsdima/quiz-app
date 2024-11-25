package com.dp.domain.repository

import com.dp.domain.model.GameSession
import com.dp.domain.model.GameStatus
import com.dp.domain.model.NewGameSession
import com.dp.domain.model.NewUserAnswer
import kotlinx.coroutines.flow.Flow

/**
 * Repository for managing game session data.
 */
interface GameSessionRepository {

    /**
     * Flow of all game sessions.
     */
    val gameSessions: Flow<List<GameSession>>

    /**
     * Adds a new game session.
     *
     * @param gameSession The game session to add.
     * @return The ID of the added game session.
     */
    suspend fun addGameSession(gameSession: NewGameSession): Long

    /**
     * Fetches a game session by its ID.
     *
     * @param gameSessionId The ID of the game session to fetch.
     * @return A flow emitting the requested game session.
     */
    fun getGameSession(gameSessionId: Long): Flow<GameSession>

    /**
     * Updates a game session with new values.
     *
     * @param gameSessionId The ID of the game session to update.
     * @param newGameStatus The new game status to set.
     * @param newStartedAt The new start timestamp.
     * @param newFinishedAt The new finish timestamp.
     * @param newGameDuration The new game duration.
     */
    suspend fun updateGameSession(
        gameSessionId: Long,
        newGameStatus: GameStatus? = null,
        newStartedAt: Long? = null,
        newFinishedAt: Long? = null,
        newGameDuration: Long? = null,
    )

    /**
     * Adds a question to a game session.
     *
     * @param gameSessionId The ID of the game session.
     * @param questionId The ID of the question to add.
     */
    suspend fun addQuestionToGame(gameSessionId: Long, questionId: Long)

    /**
     * Adds a user answer to the game session.
     *
     * @param userAnswer The user answer to add.
     */
    suspend fun addUserAnswer(userAnswer: NewUserAnswer)
}