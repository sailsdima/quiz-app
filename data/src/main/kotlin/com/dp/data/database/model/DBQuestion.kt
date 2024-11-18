package com.dp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.dp.data.database.TableNames.TABLE_QUESTION
import com.dp.domain.model.QuestionType

@Entity(
    tableName = TABLE_QUESTION,
    foreignKeys = [ForeignKey(
        entity = DBCategory::class,
        parentColumns = arrayOf("c_id"),
        childColumns = arrayOf("q_category_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class DBQuestion(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "q_id") val id: Long,
    @ColumnInfo(name = "q_type") val type: QuestionType,
    @ColumnInfo(name = "q_category_id", index = true) val categoryId: Long,
    @ColumnInfo(name = "q_question") val question: String,
    @ColumnInfo(name = "q_difficulty") val difficulty: Int,
    @ColumnInfo(name = "q_createdAt") val createdAt: Long,
    @ColumnInfo(name = "q_updatedAt") val updatedAt: Long,
    @ColumnInfo(name = "q_timesShown") val timesShown: Int,
)