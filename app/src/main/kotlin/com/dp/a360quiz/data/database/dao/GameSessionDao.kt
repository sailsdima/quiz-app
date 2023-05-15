package com.dp.a360quiz.data.database.dao

import androidx.room.*
import com.dp.a360quiz.data.database.TableNames.TABLE_GAME_SESSION
import com.dp.a360quiz.data.database.model.DBGameSession
import com.dp.a360quiz.data.database.model.DBGameWithQuestionsCrossRef
import com.dp.a360quiz.data.database.model.DBUserAnswer
import com.dp.a360quiz.data.database.model.GameWithQuestions
import com.dp.a360quiz.data.database.model.update.GameSessionUpdateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameSessionDao {

    @Insert
    fun insertGameSession(gameSession: DBGameSession): Long

    @Insert
    fun insertUserAnswer(userAnswer: DBUserAnswer)

    @Update(entity = DBGameSession::class)
    fun updateGameSession(gameSessionUpdate: GameSessionUpdateEntity)

    @Insert
    fun insertGameWithQuestionsCrossRef(gameWithQuestions: DBGameWithQuestionsCrossRef)

    @Transaction
    @Query("SELECT * FROM $TABLE_GAME_SESSION ORDER BY g_finishedAt DESC")
    fun getGameSessions(): Flow<List<GameWithQuestions>>

    @Transaction
    @Query("SELECT * FROM $TABLE_GAME_SESSION WHERE g_id == :gameId")
    fun getGameSession(gameId: Long): Flow<GameWithQuestions>
}