package com.dp.data.repository

import com.dp.data.database.converter.DBEntitiesConverter.toDBGameSession
import com.dp.data.database.converter.DBEntitiesConverter.toDBUserAnswer
import com.dp.data.database.converter.DBEntitiesConverter.toGameSession
import com.dp.data.database.converter.DBEntitiesConverter.toGameSessions
import com.dp.data.database.dao.GameSessionDao
import com.dp.data.database.model.DBGameWithQuestionsCrossRef
import com.dp.data.database.model.update.GameSessionUpdateEntity
import com.dp.core.di.IoDispatcher
import com.dp.domain.model.GameSession
import com.dp.domain.model.GameStatus
import com.dp.domain.model.NewGameSession
import com.dp.domain.model.NewUserAnswer
import com.dp.domain.repository.GameSessionRepository
import com.dp.domain.time.CurrentTimeProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameSessionRepositoryImpl @Inject constructor(
    private val gameSessionDao: GameSessionDao,
    private val currentTimeProvider: CurrentTimeProvider
) : GameSessionRepository {

    override val gameSessions: Flow<List<GameSession>>
        get() = gameSessionDao.getGameSessions().map { it.toGameSessions() }

    override suspend fun addGameSession(gameSession: NewGameSession) =
        gameSessionDao.insertGameSession(gameSession.toDBGameSession())

    override fun getGameSession(gameSessionId: Long): Flow<GameSession> =
        gameSessionDao.getGameSession(gameSessionId).map { it.toGameSession() }

    override suspend fun updateGameSession(
        gameSessionId: Long,
        newGameStatus: GameStatus?,
        newStartedAt: Long?,
        newFinishedAt: Long?,
        newGameDuration: Long?,
    ) {
        val updateEntity = GameSessionUpdateEntity(gameSessionId, newGameStatus, newStartedAt, newFinishedAt, newGameDuration)
        gameSessionDao.updateGameSession(updateEntity)
    }

    override suspend fun addQuestionToGame(gameSessionId: Long, questionId: Long) {
        val addedAt = currentTimeProvider.currentTime
        val gameWithQuestion = DBGameWithQuestionsCrossRef(gameSessionId, questionId, addedAt)
        gameSessionDao.insertGameWithQuestionsCrossRef(gameWithQuestion)
    }

    override suspend fun addUserAnswer(userAnswer: NewUserAnswer) {
        gameSessionDao.insertUserAnswer(userAnswer.toDBUserAnswer())
    }
}