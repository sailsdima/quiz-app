package com.dp.a360quiz.domain.model

import com.dp.a360quiz.common.annotation.QuestionType

open class Question(
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