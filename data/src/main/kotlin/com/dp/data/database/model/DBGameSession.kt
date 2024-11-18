package com.dp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dp.data.database.TableNames.TABLE_GAME_SESSION
import com.dp.domain.model.GameStatus

@Entity(tableName = TABLE_GAME_SESSION)
data class DBGameSession(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "g_id") val id: Long,
    @ColumnInfo(name = "g_time_per_question_ms") val timePerGameMs: Long,
    @ColumnInfo(name = "g_questions_count") val questionsCount: Int,
    @ColumnInfo(name = "g_mistakes_allowed_count") val mistakesAllowedCount: Int,
    @ColumnInfo(name = "g_game_status") val gameStatus: GameStatus,
    @ColumnInfo(name = "g_createdAt") val createdAt: Long,
    @ColumnInfo(name = "g_startedAt") val startedAt: Long?,
    @ColumnInfo(name = "g_finishedAt") val finishedAt: Long?,
    @ColumnInfo(name = "g_gameDuration") val gameDuration: Long?,
)