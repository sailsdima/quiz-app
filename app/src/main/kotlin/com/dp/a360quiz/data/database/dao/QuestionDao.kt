package com.dp.a360quiz.data.database.dao

import androidx.room.*
import com.dp.a360quiz.data.database.TableNames.TABLE_QUESTION
import com.dp.a360quiz.data.database.model.DBQuestion
import com.dp.a360quiz.data.database.model.DeleteQuestionEntity
import com.dp.a360quiz.data.database.model.QuestionWithAnswers
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Query("SELECT * FROM $TABLE_QUESTION")
    @Transaction
    fun getQuestions(): Flow<QuestionWithAnswers>

    @Query("SELECT q_id FROM $TABLE_QUESTION")
    @Transaction
    fun getQuestionsList(): List<Long>

    @Upsert
    fun upsertQuestion(question: DBQuestion): Long

    @Transaction
    @Query("SELECT * FROM $TABLE_QUESTION ORDER BY q_timesShown, RANDOM() ASC LIMIT 1")
    fun getRandomQuestion(): QuestionWithAnswers

    @Transaction
    @Query("SELECT * FROM $TABLE_QUESTION WHERE q_id == :questionId")
    fun getQuestionById(questionId: Long): QuestionWithAnswers

    @Transaction
    @Query("UPDATE $TABLE_QUESTION SET q_timesShown = q_timesShown + 1 WHERE q_id = :questionId")
    fun incrementQuestionShownTimesCount(questionId: Long)

    @Delete
    fun deleteQuestion(question: DBQuestion)

    @Delete(entity = DBQuestion::class)
    fun deleteQuestions(questions: Array<DeleteQuestionEntity>)

    @Query("DELETE FROM $TABLE_QUESTION")
    fun clear()
}