package com.dp.data.default_data.converter

import com.dp.data.default_data.model.GDefaultAnswer
import com.dp.data.default_data.model.GDefaultQuestion
import com.dp.data.default_data.model.GDefaultQuestions
import com.dp.domain.model.Answer
import com.dp.domain.model.Category
import com.dp.domain.model.Question
import com.dp.domain.model.QuestionType
import com.dp.domain.model.default_data.DefaultAnswer
import com.dp.domain.model.default_data.DefaultQuestion
import com.dp.domain.model.default_data.DefaultQuestions

private fun GDefaultAnswer.toDefaultAnswer(): DefaultAnswer =
    DefaultAnswer(
        answer = answer,
        isCorrect = isCorrect
    )


private fun GDefaultQuestion.toDefaultQuestion(): DefaultQuestion = DefaultQuestion(
    id = id,
    type = type,
    category = category,
    question = question,
    difficulty = difficulty,
    answers = answers.map { it.toDefaultAnswer() }
)

fun GDefaultQuestions.toDefaultQuestions(createdAt: Long): DefaultQuestions =
    DefaultQuestions(
        version = version,
        questions = questions.map { it.toQuestion(createdAt) }
    )


fun GDefaultQuestions.toQuestions(createdAt: Long): List<Question> {
    return questions.map { it.toQuestion(createdAt) }
}

private fun GDefaultQuestion.toQuestion(createdAt: Long): Question {
    return Question(
        id = id,
        type = QuestionType.getQuestionType(type),
        category = Category(0, category),
        question = question,
        difficulty = difficulty,
        createdAt = createdAt,
        updatedAt = createdAt,
        timesShown = 0,
        answers = answers.map { Answer(0, it.answer, it.isCorrect) },
    )
}
