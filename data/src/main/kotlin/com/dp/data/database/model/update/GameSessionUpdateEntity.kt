package com.dp.data.database.model.update

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dp.domain.model.GameStatus

@Entity
data class GameSessionUpdateEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "g_id") val id: Long,
    @ColumnInfo(name = "g_game_status") val gameStatus: GameStatus?,
    @ColumnInfo(name = "g_startedAt") val startedAt: Long?,
    @ColumnInfo(name = "g_finishedAt") val finishedAt: Long?,
    @ColumnInfo(name = "g_gameDuration") val gameDuration: Long?,
)