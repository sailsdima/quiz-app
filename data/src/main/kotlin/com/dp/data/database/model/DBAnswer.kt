package com.dp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dp.data.database.TableNames.TABLE_ANSWER

/**
 * Represents an answer to a question.
 *
 * @property id The unique identifier for the answer.
 * @property questionId The ID of the question that this answer belongs to.
 * @property value The content or text of the answer.
 * @property isCorrect A flag indicating whether the answer is correct.
 *
 * This table is related to the [DBQuestion] table by the `questionId` field.
 */
@Entity(
    tableName = TABLE_ANSWER,
    foreignKeys = [ForeignKey(
        entity = DBQuestion::class,
        parentColumns = arrayOf("q_id"),
        childColumns = arrayOf("a_questionId"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("a_questionId")]
)
data class DBAnswer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "a_id") val id: Long,
    @ColumnInfo(name = "a_questionId") val questionId: Long,
    @ColumnInfo(name = "a_value") val value: String,
    @ColumnInfo(name = "a_isCorrect") val isCorrect: Boolean,
)