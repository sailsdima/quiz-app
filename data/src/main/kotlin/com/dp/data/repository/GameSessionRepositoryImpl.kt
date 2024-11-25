package com.dp.data.repository

import com.dp.data.database.converter.DBEntitiesConverter.toDBGameSession
import com.dp.data.database.converter.DBEntitiesConverter.toDBUserAnswer
import com.dp.data.database.converter.DBEntitiesConverter.toGameSession
import com.dp.data.database.converter.DBEntitiesConverter.toGameSessions
import com.dp.data.database.dao.GameSessionDao
import com.dp.data.database.model.DBGameWithQuestionsCrossRef
import com.dp.data.database.model.update.GameSessionUpdateEntity
import com.dp.domain.model.GameSession
import com.dp.domain.model.GameStatus
import com.dp.domain.model.NewGameSession
import com.dp.domain.model.NewUserAnswer
import com.dp.domain.repository.GameSessionRepository
import com.dp.domain.time.CurrentTimeProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of [GameSessionRepository] that interacts with the local database.
 */
class GameSessionRepositoryImpl @Inject constructor(
    private val gameSessionDao: GameSessionDao,
    private val currentTimeProvider: CurrentTimeProvider
) : GameSessionRepository {

    /**
     * A flow that emits all game sessions in the database.
     */
    override val gameSessions: Flow<List<GameSession>>
        get() = gameSessionDao.getGameSessions().map { it.toGameSessions() }

    /**
     * Adds a new game session to the database.
     *
     * @param gameSession The new game session to add.
     * @return The ID of the added game session.
     */
    override suspend fun addGameSession(gameSession: NewGameSession): Long =
        gameSessionDao.insertGameSession(gameSession = gameSession.toDBGameSession())

    /**
     * Fetches a specific game session by its ID.
     *
     * @param gameSessionId The ID of the game session to fetch.
     * @return A flow emitting the requested game session.
     */
    override fun getGameSession(gameSessionId: Long): Flow<GameSession> =
        gameSessionDao.getGameSession(gameId = gameSessionId).map { it.toGameSession() }

    /**
     * Updates the specified game session with the new values.
     *
     * @param gameSessionId The ID of the game session to update.
     * @param newGameStatus The new game status to set.
     * @param newStartedAt The new start timestamp.
     * @param newFinishedAt The new finish timestamp.
     * @param newGameDuration The new game duration.
     */
    override suspend fun updateGameSession(
        gameSessionId: Long,
        newGameStatus: GameStatus?,
        newStartedAt: Long?,
        newFinishedAt: Long?,
        newGameDuration: Long?,
    ) {
        val updateEntity = GameSessionUpdateEntity(
            gameSessionId,
            gameStatus = newGameStatus,
            startedAt = newStartedAt,
            finishedAt = newFinishedAt,
            gameDuration = newGameDuration
        )
        gameSessionDao.updateGameSession(gameSessionUpdate = updateEntity)
    }

    /**
     * Adds a question to the specified game session.
     *
     * @param gameSessionId The ID of the game session.
     * @param questionId The ID of the question to add.
     */
    override suspend fun addQuestionToGame(gameSessionId: Long, questionId: Long) {
        val addedAt = currentTimeProvider.currentTime
        val gameWithQuestion = DBGameWithQuestionsCrossRef(
            gameId = gameSessionId,
            questionId = questionId,
            addedAt = addedAt
        )
        gameSessionDao.insertGameWithQuestionsCrossRef(gameWithQuestions = gameWithQuestion)
    }

    /**
     * Adds a user answer to the game session.
     *
     * @param userAnswer The user answer to add.
     */
    override suspend fun addUserAnswer(userAnswer: NewUserAnswer) {
        gameSessionDao.insertUserAnswer(userAnswer = userAnswer.toDBUserAnswer())
    }
}