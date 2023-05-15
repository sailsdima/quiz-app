package com.dp.a360quiz.domain.model

data class NewGameSession(
    val timePerGameMs: Long,
    val questionsCount: Int,
    val mistakesAllowedCount: Int,
    val gameStatus: GameStatus,
    val createdAt: Long,
)