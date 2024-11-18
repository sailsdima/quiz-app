package com.dp.usecase.impl.question

import com.dp.domain.repository.QuestionRepository
import com.dp.domain.usecase.question.IncrementQuestionShownTimesCountUseCase
import javax.inject.Inject

class IncrementQuestionShownTimesCountUseCaseImpl @Inject constructor(
    private val questionRepository: QuestionRepository
) : IncrementQuestionShownTimesCountUseCase {

    override suspend fun execute(questionId: Long) = questionRepository.incrementQuestionShownTimesCount(questionId)

}