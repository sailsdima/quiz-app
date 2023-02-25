package com.dp.a360quiz.domain.usecase.question

import com.dp.a360quiz.domain.model.Question
import com.dp.a360quiz.domain.repository.QuestionRepository
import javax.inject.Inject

class GetNextRandomQuestionUseCaseImpl @Inject constructor(
    private val questionRepository: QuestionRepository
) : GetNextRandomQuestionUseCase {

    override suspend fun execute(): Question = questionRepository.getNextRandomQuestion()

}