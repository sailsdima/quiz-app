package com.dp.a360quiz.domain.usecase.question

interface IncrementQuestionShownTimesCountUseCase {
    suspend fun execute(questionId: Long)
}