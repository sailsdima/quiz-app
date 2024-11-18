package com.dp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dp.data.database.TableNames.TABLE_USER_ANSWER

@Entity(tableName = TABLE_USER_ANSWER)
data class DBUserAnswer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ua_id") val id: Long,
    @ColumnInfo(name = "ua_game_id") val gameId: Long,
    @ColumnInfo(name = "ua_question_id") val questionId: Long,
    @ColumnInfo(name = "ua_answer_id") val answerId: Long,
    @ColumnInfo(name = "ua_answered_at") val answeredAt: Long,
    @ColumnInfo(name = "ua_is_correct") val isCorrect: Boolean,
)