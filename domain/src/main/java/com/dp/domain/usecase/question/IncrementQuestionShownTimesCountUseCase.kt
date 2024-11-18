package com.dp.domain.usecase.question

interface IncrementQuestionShownTimesCountUseCase {
    suspend fun execute(questionId: Long)
}