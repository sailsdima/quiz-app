package com.dp.usecase.impl.question

import com.dp.domain.model.Question
import com.dp.domain.repository.QuestionRepository
import com.dp.domain.usecase.question.GetNextRandomQuestionUseCase
import javax.inject.Inject

class GetNextRandomQuestionUseCaseImpl @Inject constructor(
    private val questionRepository: QuestionRepository
) : GetNextRandomQuestionUseCase {

    override suspend fun execute(): Question = questionRepository.getNextRandomQuestion()

}