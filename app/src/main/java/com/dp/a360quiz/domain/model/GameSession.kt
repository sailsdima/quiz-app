package com.dp.a360quiz.domain.model

import com.dp.a360quiz.domain.usecase.GameSummaryStatus

data class GameSession(
    val id: Long,
    val timePerGameMs: Long,
    val questionsCount: Int,
    val mistakesAllowedCount: Int,
    val gameStatus: GameStatus,
    val createdAt: Long,
    val startedAt: Long?,
    val finishedAt: Long?,
    val gameDuration: Long?,
    val questions: List<Question>,
    val userAnswers: List<UserAnswer>,
)

fun GameSession.getQuestionById(questionId: Long) = questions.find { it.id == questionId }
fun GameSession.incorrectAnswersCount() = userAnswers.count { !it.isCorrect }
fun GameSession.correctAnswersCount() = userAnswers.count { it.isCorrect }
fun GameSession.answersCount() = userAnswers.size
fun GameSession.livesLeft() = mistakesAllowedCount - incorrectAnswersCount()
fun GameSession.score() = userAnswers.filter { it.isCorrect }
    .mapNotNull { userAnswer -> questions.firstOrNull { userAnswer.questionId == it.id } }
    .sumOf { it.difficulty }

fun GameSession.gameSummaryStatus() =
    if (questionsCount == answersCount() && incorrectAnswersCount() <= mistakesAllowedCount)
        GameSummaryStatus.WON
    else
        GameSummaryStatus.LOST

fun List<GameSession>.wonGamesCount() = count { it.gameSummaryStatus() == GameSummaryStatus.WON }