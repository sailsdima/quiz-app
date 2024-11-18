package com.dp.domain.model

data class Question(
    val id: Long,
    val type: QuestionType,
    val category: Category,
    val question: String,
    val difficulty: Int,
    val createdAt: Long,
    val updatedAt: Long,
    val timesShown: Int,
    val answers: List<Answer>
)

fun Question.getCorrectAnswerId(): Long? = answers.find { it.isCorrect }?.id