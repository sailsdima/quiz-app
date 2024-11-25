package com.dp.domain.usecase.question

import com.dp.domain.model.Question

/**
 * Use case interface for retrieving the next random question.
 *
 * This use case provides a mechanism to fetch the next random question from the repository.
 */
interface GetNextRandomQuestionUseCase {

    /**
     * Retrieves the next random question.
     *
     * @return The next random [Question] object.
     */
    suspend operator fun invoke(): Question
}