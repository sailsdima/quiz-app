package com.dp.a360quiz.domain.usecase

import com.dp.domain.model.Answer
import com.dp.domain.model.GameSession
import com.dp.domain.model.Question
import com.dp.domain.model.answersCount
import com.dp.domain.model.incorrectAnswersCount


fun Question.toQuestionUIEntity(
    userAnswerId: Long? = null,
    correctAnswerId: Long? = null,
): QuestionUIEntity {
    return QuestionUIEntity(
        id = id,
        type = type,
        question = question,
        answers = answers.toAnswerUIEntities(userAnswerId, correctAnswerId),
    )
}

private fun List<Answer>.toAnswerUIEntities(
    userAnswerId: Long?,
    correctAnswerId: Long?,
): List<AnswerUIEntity> {
    return map { it.toAnswerUIEntity(userAnswerId, correctAnswerId) }
}

private fun Answer.toAnswerUIEntity(userAnswerId: Long?, correctAnswerId: Long?): AnswerUIEntity {
    return AnswerUIEntity(
        id = id,
        answer = value,
        answerType = when {
            id == userAnswerId && userAnswerId == correctAnswerId -> UIAnswerType.USER_CORRECT_ANSWER
            id == userAnswerId && userAnswerId != correctAnswerId -> UIAnswerType.ANSWERED_WRONG
            id == correctAnswerId -> UIAnswerType.CORRECT_ANSWER
            else -> UIAnswerType.NOT_ANSWERED
        }
    )
}


fun GameSession.gameSummaryStatus() =
    if (questionsCount == answersCount() && incorrectAnswersCount() <= mistakesAllowedCount)
        GameSummaryStatus.WON
    else
        GameSummaryStatus.LOST

fun List<GameSession>.wonGamesCount() = count { it.gameSummaryStatus() == GameSummaryStatus.WON }