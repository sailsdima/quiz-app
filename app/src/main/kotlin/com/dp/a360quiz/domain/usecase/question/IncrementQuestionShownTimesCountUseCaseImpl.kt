package com.dp.a360quiz.domain.usecase.question

import com.dp.a360quiz.domain.repository.QuestionRepository
import javax.inject.Inject

class IncrementQuestionShownTimesCountUseCaseImpl @Inject constructor(
    private val questionRepository: QuestionRepository
) : IncrementQuestionShownTimesCountUseCase {

    override suspend fun execute(questionId: Long) = questionRepository.incrementQuestionShownTimesCount(questionId)

}