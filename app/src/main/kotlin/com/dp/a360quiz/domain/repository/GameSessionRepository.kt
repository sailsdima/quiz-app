package com.dp.a360quiz.domain.repository

import com.dp.a360quiz.domain.model.GameSession
import com.dp.a360quiz.domain.model.GameStatus
import com.dp.a360quiz.domain.model.NewGameSession
import com.dp.a360quiz.domain.model.NewUserAnswer
import kotlinx.coroutines.flow.Flow

interface GameSessionRepository {

    val gameSessions: Flow<List<GameSession>>

    suspend fun addGameSession(gameSession: NewGameSession): Long

    suspend fun getGameSession(gameSessionId: Long): Flow<GameSession>

    suspend fun updateGameSession(
        gameSessionId: Long,
        newGameStatus: GameStatus? = null,
        newStartedAt: Long? = null,
        newFinishedAt: Long? = null,
        newGameDuration: Long? = null,
    )

    suspend fun addQuestionToGame(gameSessionId: Long, questionId: Long)

    suspend fun addUserAnswer(userAnswer: NewUserAnswer)

}