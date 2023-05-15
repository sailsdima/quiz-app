package com.dp.a360quiz.data.repository

import com.dp.a360quiz.data.converters.DBEntitiesConverter.toDBGameSession
import com.dp.a360quiz.data.converters.DBEntitiesConverter.toDBUserAnswer
import com.dp.a360quiz.data.converters.DBEntitiesConverter.toGameSession
import com.dp.a360quiz.data.converters.DBEntitiesConverter.toGameSessions
import com.dp.a360quiz.data.database.dao.GameSessionDao
import com.dp.a360quiz.data.database.model.DBGameWithQuestionsCrossRef
import com.dp.a360quiz.data.database.model.update.GameSessionUpdateEntity
import com.dp.a360quiz.di.module.IoDispatcher
import com.dp.a360quiz.domain.model.GameSession
import com.dp.a360quiz.domain.model.GameStatus
import com.dp.a360quiz.domain.model.NewGameSession
import com.dp.a360quiz.domain.model.NewUserAnswer
import com.dp.a360quiz.domain.repository.GameSessionRepository
import com.dp.a360quiz.domain.time.CurrentTimeProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameSessionRepositoryImpl @Inject constructor(
    private val gameSessionDao: GameSessionDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val currentTimeProvider: CurrentTimeProvider
) : GameSessionRepository {

    override val gameSessions: Flow<List<GameSession>>
        get() = gameSessionDao.getGameSessions().map { it.toGameSessions() }
            .flowOn(ioDispatcher)

    override suspend fun addGameSession(gameSession: NewGameSession) = withContext(ioDispatcher) {
        gameSessionDao.insertGameSession(gameSession.toDBGameSession())
    }

    override suspend fun getGameSession(gameSessionId: Long): Flow<GameSession> {
        return gameSessionDao.getGameSession(gameSessionId).map { it.toGameSession() }
    }

    override suspend fun updateGameSession(
        gameSessionId: Long,
        newGameStatus: GameStatus?,
        newStartedAt: Long?,
        newFinishedAt: Long?,
        newGameDuration: Long?,
    ) = withContext(ioDispatcher) {
        val updateEntity =
            GameSessionUpdateEntity(gameSessionId, newGameStatus, newStartedAt, newFinishedAt, newGameDuration)
        gameSessionDao.updateGameSession(updateEntity)
    }

    override suspend fun addQuestionToGame(gameSessionId: Long, questionId: Long): Unit =
        withContext(ioDispatcher) {
            val addedAt = currentTimeProvider.currentTime
            val gameWithQuestion = DBGameWithQuestionsCrossRef(gameSessionId, questionId, addedAt)
            gameSessionDao.insertGameWithQuestionsCrossRef(gameWithQuestion)
        }

    override suspend fun addUserAnswer(userAnswer: NewUserAnswer): Unit = withContext(ioDispatcher) {
        gameSessionDao.insertUserAnswer(userAnswer.toDBUserAnswer())
    }
}