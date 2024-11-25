package com.dp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.dp.data.database.TableNames.TABLE_QUESTION
import com.dp.data.database.model.DBQuestion
import com.dp.data.database.model.QuestionWithAnswers
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    /**
     * Retrieves all questions from the database, including their associated answers.
     *
     * @return A Flow containing a list of all questions with their associated answers.
     */
    @Query("SELECT * FROM $TABLE_QUESTION")
    @Transaction
    fun getQuestions(): Flow<QuestionWithAnswers>

    /**
     * Retrieves a list of all question IDs.
     *
     * @return A list of question IDs.
     */
    @Query("SELECT q_id FROM $TABLE_QUESTION")
    @Transaction
    suspend fun getQuestionsList(): List<Long>

    /**
     * Inserts or updates the given question in the database.
     * If the question already exists, it will be updated.
     *
     * @param question The question to be inserted or updated.
     * @return The ID of the inserted or updated question.
     */
    @Upsert
    suspend fun upsertQuestion(question: DBQuestion): Long

    /**
     * Retrieves a random question from the database, ordered by the number of times it has been shown.
     *
     * @return A random question with its associated answers.
     */
    @Transaction
    @Query("SELECT * FROM $TABLE_QUESTION ORDER BY q_timesShown, RANDOM() ASC LIMIT 1")
    suspend fun getRandomQuestion(): QuestionWithAnswers

    /**
     * Retrieves a question by its ID, including its associated answers.
     *
     * @param questionId The ID of the question to be retrieved.
     * @return The question with its associated answers.
     */
    @Transaction
    @Query("SELECT * FROM $TABLE_QUESTION WHERE q_id == :questionId")
    suspend fun getQuestionById(questionId: Long): QuestionWithAnswers

    /**
     * Increments the `q_timesShown` field of a specific question by 1.
     *
     * @param questionId The ID of the question whose `q_timesShown` field will be incremented.
     */
    @Transaction
    @Query("UPDATE $TABLE_QUESTION SET q_timesShown = q_timesShown + 1 WHERE q_id = :questionId")
    suspend fun incrementShownCount(questionId: Long)

    /**
     * Deletes a specific question from the database.
     *
     * @param question The question to be deleted.
     */
    @Delete
    suspend fun deleteQuestion(question: DBQuestion)

    /**
     * Deletes a list of questions from the database by their IDs.
     *
     * @param questionIds The list of question IDs to be deleted.
     */
    @Query("DELETE FROM $TABLE_QUESTION WHERE q_id IN (:questionIds)")
    suspend fun deleteQuestionsByIds(questionIds: List<Long>)

    /**
     * Deletes all questions from the database.
     */
    @Query("DELETE FROM $TABLE_QUESTION")
    suspend fun clear()
}
