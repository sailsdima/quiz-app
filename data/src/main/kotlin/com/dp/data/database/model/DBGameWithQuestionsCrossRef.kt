package com.dp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.dp.data.database.TableNames.TABLE_GAME_QUESTIONS_CROSS_REF

@Entity(
    tableName = TABLE_GAME_QUESTIONS_CROSS_REF,
    primaryKeys = ["game_id", "question_id", "added_at"],
    indices = [Index(value = ["question_id"])],
    foreignKeys = [
        ForeignKey(
            entity = DBGameSession::class,
            parentColumns = ["g_id"],
            childColumns = ["game_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = DBQuestion::class,
            parentColumns = ["q_id"],
            childColumns = ["question_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ]
)
data class DBGameWithQuestionsCrossRef(
    @ColumnInfo(name = "game_id") val gameId: Long,
    @ColumnInfo(name = "question_id") val questionId: Long,
    @ColumnInfo(name = "added_at") val addedAt: Long,
)