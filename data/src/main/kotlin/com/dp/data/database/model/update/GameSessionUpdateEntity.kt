package com.dp.data.database.model.update

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dp.domain.model.GameStatus

/**
 * Represents an update to an existing game session, capturing changes in the game's status and duration.
 *
 * @property id The unique identifier for the game session update.
 * @property gameStatus The current status of the game (e.g., in progress, completed).
 * @property startedAt The timestamp of when the game session started.
 * @property finishedAt The timestamp of when the game session finished.
 * @property gameDuration The total duration of the game in milliseconds.
 *
 * This entity is used to store updates to an existing game session. It allows tracking changes
 * to the game state, such as when the game starts, finishes, and its total duration.
 */
@Entity
data class GameSessionUpdateEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "g_id") val id: Long,
    @ColumnInfo(name = "g_game_status") val gameStatus: GameStatus?,
    @ColumnInfo(name = "g_startedAt") val startedAt: Long?,
    @ColumnInfo(name = "g_finishedAt") val finishedAt: Long?,
    @ColumnInfo(name = "g_gameDuration") val gameDuration: Long?,
)