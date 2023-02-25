package com.dp.a360quiz.domain.model

data class Answer(
    val id: Long,
    val value: String,
    val isCorrect: Boolean,
)