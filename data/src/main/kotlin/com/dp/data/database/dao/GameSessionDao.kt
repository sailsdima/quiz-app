package com.dp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.dp.data.database.TableNames.TABLE_GAME_SESSION
import com.dp.data.database.model.DBGameSession
import com.dp.data.database.model.DBGameWithQuestionsCrossRef
import com.dp.data.database.model.DBUserAnswer
import com.dp.data.database.model.GameWithQuestions
import com.dp.data.database.model.update.GameSessionUpdateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameSessionDao {

    @Insert
    suspend fun insertGameSession(gameSession: DBGameSession): Long

    @Insert
    suspend fun insertUserAnswer(userAnswer: DBUserAnswer)

    @Update(entity = DBGameSession::class)
    suspend fun updateGameSession(gameSessionUpdate: GameSessionUpdateEntity)

    @Insert
    suspend fun insertGameWithQuestionsCrossRef(gameWithQuestions: DBGameWithQuestionsCrossRef)

    @Transaction
    @Query("SELECT * FROM $TABLE_GAME_SESSION ORDER BY g_finishedAt DESC")
    fun getGameSessions(): Flow<List<GameWithQuestions>>

    @Transaction
    @Query("SELECT * FROM $TABLE_GAME_SESSION WHERE g_id == :gameId")
    fun getGameSession(gameId: Long): Flow<GameWithQuestions>
}