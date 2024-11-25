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

    /**
     * Inserts a new game session into the database.
     *
     * @param gameSession The game session to be inserted.
     * @return The ID of the newly inserted game session.
     */
    @Insert
    suspend fun insertGameSession(gameSession: DBGameSession): Long

    /**
     * Inserts a user's answer for a specific game session into the database.
     *
     * @param userAnswer The user answer to be inserted.
     */
    @Insert
    suspend fun insertUserAnswer(userAnswer: DBUserAnswer)

    /**
     * Updates a game session with new details.
     * This function updates the fields of the game session identified by the provided `GameSessionUpdateEntity`.
     *
     * @param gameSessionUpdate The updated game session details.
     */
    @Update(entity = DBGameSession::class)
    suspend fun updateGameSession(gameSessionUpdate: GameSessionUpdateEntity)

    /**
     * Inserts a cross-reference entry that associates a game session with a list of questions.
     *
     * @param gameWithQuestions The cross-reference entry to be inserted.
     */
    @Insert
    suspend fun insertGameWithQuestionsCrossRef(gameWithQuestions: DBGameWithQuestionsCrossRef)

    /**
     * Retrieves all game sessions ordered by the finished timestamp in descending order.
     * This includes the related game questions and user answers.
     *
     * @return A flow of a list of game sessions, each including the associated questions and user answers.
     */
    @Transaction
    @Query("SELECT * FROM $TABLE_GAME_SESSION ORDER BY g_finishedAt DESC")
    fun getGameSessions(): Flow<List<GameWithQuestions>>

    /**
     * Retrieves a specific game session by its ID, including the related questions and user answers.
     *
     * @param gameId The ID of the game session to retrieve.
     * @return A flow containing the requested game session along with the associated questions and user answers.
     */
    @Transaction
    @Query("SELECT * FROM $TABLE_GAME_SESSION WHERE g_id == :gameId")
    fun getGameSession(gameId: Long): Flow<GameWithQuestions>
}
