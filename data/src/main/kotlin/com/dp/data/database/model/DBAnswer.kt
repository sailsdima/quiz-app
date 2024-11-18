package com.dp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.dp.data.database.TableNames.TABLE_ANSWER

@Entity(
    tableName = TABLE_ANSWER,
    foreignKeys = [ForeignKey(
        entity = DBQuestion::class,
        parentColumns = arrayOf("q_id"),
        childColumns = arrayOf("a_questionId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class DBAnswer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "a_id") val id: Long,
    @ColumnInfo(name = "a_questionId", index = true) val questionId: Long,
    @ColumnInfo(name = "a_value") val value: String,
    @ColumnInfo(name = "a_isCorrect") val isCorrect: Boolean,
)