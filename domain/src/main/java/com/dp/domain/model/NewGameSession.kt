package com.dp.domain.model

data class NewGameSession(
    val timePerGameMs: Long,
    val questionsCount: Int,
    val mistakesAllowedCount: Int,
    val gameStatus: GameStatus,
    val createdAt: Long,
)