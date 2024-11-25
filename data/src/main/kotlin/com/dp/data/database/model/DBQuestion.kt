package com.dp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dp.data.database.TableNames.TABLE_QUESTION
import com.dp.domain.model.QuestionType

/**
 * Represents a question in the system.
 *
 * @property id The unique identifier for the question.
 * @property type The type of the question (e.g., multiple choice, true/false).
 * @property categoryId The ID of the category this question belongs to.
 * @property question The actual question text.
 * @property difficulty The difficulty level of the question (represented as an integer).
 * @property createdAt The timestamp of when the question was created.
 * @property updatedAt The timestamp of when the question was last updated.
 * @property timesShown The number of times this question has been shown.
 *
 * This table stores the metadata for each question, such as its content, category, and difficulty.
 * It is related to the [DBCategory] table by the `categoryId` foreign key.
 */
@Entity(
    tableName = TABLE_QUESTION,
    foreignKeys = [ForeignKey(
        entity = DBCategory::class,
        parentColumns = arrayOf("c_id"),
        childColumns = arrayOf("q_category_id"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("q_category_id"), Index("q_difficulty")]
)
data class DBQuestion(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "q_id") val id: Long,
    @ColumnInfo(name = "q_type") val type: QuestionType,
    @ColumnInfo(name = "q_category_id") val categoryId: Long,
    @ColumnInfo(name = "q_question") val question: String,
    @ColumnInfo(name = "q_difficulty") val difficulty: Int,
    @ColumnInfo(name = "q_createdAt") val createdAt: Long,
    @ColumnInfo(name = "q_updatedAt") val updatedAt: Long,
    @ColumnInfo(name = "q_timesShown") val timesShown: Int,
)