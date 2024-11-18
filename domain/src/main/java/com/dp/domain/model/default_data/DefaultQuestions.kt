package com.dp.domain.model.default_data

import com.dp.domain.model.Question

data class DefaultQuestions(
    val version: Int,
    val questions: List<Question>,
)