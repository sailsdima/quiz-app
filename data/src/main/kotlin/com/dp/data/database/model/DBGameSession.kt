package com.dp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dp.data.database.TableNames.TABLE_GAME_SESSION
import com.dp.domain.model.GameStatus

/**
 * Represents a game session in the system.
 *
 * @property id The unique identifier for the game session.
 * @property timePerGameMs The time allotted per question in milliseconds.
 * @property questionsCount The total number of questions in the game.
 * @property mistakesAllowedCount The number of mistakes allowed in the game.
 * @property gameStatus The current status of the game (e.g., in progress, completed).
 * @property createdAt The timestamp of when the game session was created.
 * @property startedAt The timestamp of when the game session started.
 * @property finishedAt The timestamp of when the game session finished.
 * @property gameDuration The total duration of the game in milliseconds.
 *
 * This table is used to store the metadata of a game session, including the game configuration,
 * status, and timestamps.
 */
@Entity(
    tableName = TABLE_GAME_SESSION,
    indices = [
        Index("g_game_status"),
        Index("g_startedAt"),
        Index("g_finishedAt"),
    ]
)
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