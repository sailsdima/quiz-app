package com.dp.a360quiz.domain.usecase.question

import com.dp.a360quiz.domain.model.Question

interface GetNextRandomQuestionUseCase {
    suspend fun execute(): Question
}