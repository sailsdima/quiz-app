package com.dp.domain.model.default_data

data class DefaultQuestion(
    val id: Long,
    val type: String,
    val category: String,
    val question: String,
    val difficulty: Int,
    val answers: List<DefaultAnswer>,
)