package com.dp.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.dp.data.database.TableNames.TABLE_ANSWER
import com.dp.data.database.model.DBAnswer
import kotlinx.coroutines.flow.Flow

@Dao
interface AnswerDao {

    /**
     * Inserts or updates a list of answers in the database.
     * If an answer already exists, it will be updated.
     *
     * @param answers The list of answers to be inserted or updated.
     */
    @Upsert
    suspend fun insertAnswers(answers: List<DBAnswer>)

    /**
     * Retrieves a specific answer by its ID.
     *
     * @param answerId The ID of the answer to be retrieved.
     * @return A Flow containing the answer with the given ID.
     */
    @Query("SELECT * FROM $TABLE_ANSWER WHERE a_id == :answerId")
    fun getAnswer(answerId: Long): Flow<DBAnswer?>
}