package com.dp.a360quiz.domain.model

data class NewUserAnswer(
    val gameId: Long,
    val questionId: Long,
    val answerId: Long,
    val answeredAt: Long,
    val isCorrect: Boolean,
)