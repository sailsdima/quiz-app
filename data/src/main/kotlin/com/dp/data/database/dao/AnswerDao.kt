package com.dp.data.database.dao

import androidx.room.*
import com.dp.data.database.TableNames.TABLE_ANSWER
import com.dp.data.database.model.DBAnswer
import kotlinx.coroutines.flow.Flow

@Dao
interface AnswerDao {

    @Upsert
    suspend fun insertAnswers(answers: List<DBAnswer>)

    @Query("SELECT * FROM $TABLE_ANSWER WHERE a_id == :answerId")
    fun getAnswer(answerId: Long): Flow<DBAnswer?>

}