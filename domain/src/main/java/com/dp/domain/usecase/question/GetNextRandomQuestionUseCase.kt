package com.dp.domain.usecase.question

import com.dp.domain.model.Question

interface GetNextRandomQuestionUseCase {
    suspend fun execute(): Question
}