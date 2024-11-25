package com.dp.usecase.impl.question

import com.dp.domain.model.Question
import com.dp.domain.repository.QuestionRepository
import com.dp.domain.usecase.question.GetNextRandomQuestionUseCase
import javax.inject.Inject

/**
 * Implementation of [GetNextRandomQuestionUseCase].
 *
 * This implementation retrieves the next random question from the repository.
 *
 * @param questionRepository The repository used to fetch the next random question.
 */
class GetNextRandomQuestionUseCaseImpl @Inject constructor(
    private val questionRepository: QuestionRepository
) : GetNextRandomQuestionUseCase {

    /**
     * Retrieves the next random question from the repository.
     *
     * @return The next random [Question] object from the repository.
     */
    override suspend fun invoke(): Question = questionRepository.getNextRandomQuestion()
}