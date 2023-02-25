package com.dp.a360quiz.data.database.dao

import androidx.room.*
import com.dp.a360quiz.data.database.TableNames.TABLE_ANSWER
import com.dp.a360quiz.data.database.model.DBAnswer
import kotlinx.coroutines.flow.Flow

@Dao
interface AnswerDao {

    @Upsert
    fun insertAnswers(answers: List<DBAnswer>)

    @Query("SELECT * FROM $TABLE_ANSWER WHERE a_id == :answerId")
    fun getAnswer(answerId: Long): Flow<DBAnswer?>

}