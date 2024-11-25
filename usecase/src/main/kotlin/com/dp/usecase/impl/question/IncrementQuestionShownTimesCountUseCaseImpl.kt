package com.dp.usecase.impl.question

import com.dp.domain.repository.QuestionRepository
import com.dp.domain.usecase.question.IncrementQuestionShownTimesCountUseCase
import javax.inject.Inject

/**
 * Implementation of [IncrementQuestionShownTimesCountUseCase].
 *
 * This implementation increments the `shownTimes` counter for the specified question in the repository.
 *
 * @param questionRepository The repository used to update the question's shown count.
 */
class IncrementQuestionShownTimesCountUseCaseImpl @Inject constructor(
    private val questionRepository: QuestionRepository
) : IncrementQuestionShownTimesCountUseCase {

    /**
     * Increments the `shownTimes` counter for the question identified by [questionId].
     *
     * @param questionId The ID of the question whose shown count will be incremented.
     */
    override suspend fun invoke(questionId: Long) =
        questionRepository.incrementQuestionShownTimesCount(questionId = questionId)
}