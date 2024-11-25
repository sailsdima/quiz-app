package com.dp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dp.data.database.TableNames.TABLE_USER_ANSWER

/**
 * Represents an answer submitted by the user during a game session.
 *
 * @property id The unique identifier for the user answer.
 * @property gameId The ID of the game session this answer belongs to.
 * @property questionId The ID of the question this answer relates to.
 * @property answerId The ID of the selected answer.
 * @property answeredAt The timestamp of when the answer was submitted.
 * @property isCorrect A flag indicating whether the user's answer was correct.
 *
 * This table stores user responses to questions during a game session. It is used to track answers
 * given by the player and compare them to correct answers.
 */
@Entity(
    tableName = TABLE_USER_ANSWER,
    indices = [Index("ua_game_id"), Index("ua_question_id"), Index("ua_answer_id")]
)
data class DBUserAnswer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ua_id") val id: Long,
    @ColumnInfo(name = "ua_game_id") val gameId: Long,
    @ColumnInfo(name = "ua_question_id") val questionId: Long,
    @ColumnInfo(name = "ua_answer_id") val answerId: Long,
    @ColumnInfo(name = "ua_answered_at") val answeredAt: Long,
    @ColumnInfo(name = "ua_is_correct") val isCorrect: Boolean,
)