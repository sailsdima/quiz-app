package com.dp.domain.usecase.question

/**
 * Use case interface for incrementing the count of times a question has been shown.
 *
 * This use case increments the `shownTimes` counter for a specific question.
 */
interface IncrementQuestionShownTimesCountUseCase {

    /**
     * Increments the shown count for the specified question.
     *
     * @param questionId The ID of the question to increment the shown count for.
     */
    suspend operator fun invoke(questionId: Long)
}