package com.dp.a360quiz.domain.usecase

import com.dp.a360quiz.common.annotation.QuestionType
import com.dp.a360quiz.domain.time.TimerState
import kotlinx.coroutines.flow.Flow

interface GameController {

    val gameEventsFlow: Flow<UIState>

    val gameTimerFlow: Flow<TimerState>

    suspend fun createAndStartGame()

    suspend fun answerQuestion(questionId: Long, answerId: Long)

    suspend fun nextQuestion()


}

sealed class UIState {
    object PendingGame : UIState()
    data class PreGameTimerState(
        val timeLeft: Long,
        val message: String,
    ) : UIState()

    data class QuestionState(
        val question: QuestionUIEntity,
        val questionsCount: Int,
        val questionNumber: Int,
        val livesLeft: Int,
    ) : UIState()

    data class QuestionAnswerSummaryState(val question: QuestionUIEntity) : UIState()

    data class GameSummaryState(
        val questionsCount: Int,
        val score: Int,
        val answeredQuestionsCount: Int,
        val correctAnswersCount: Int,
        val wrongAnswersCount: Int,
        val gameDurationSec: Long,
        val gameSummaryStatus: GameSummaryStatus
    ) : UIState()
}

data class QuestionUIEntity(
    val id: Long,
    val type: QuestionType,
    val question: String,
    val answers: List<AnswerUIEntity>
)

data class AnswerUIEntity(
    val id: Long,
    val answer: String,
    val answerType: UIAnswerType = UIAnswerType.NOT_ANSWERED
)

enum class UIAnswerType {
    NOT_ANSWERED, ANSWERED_WRONG, USER_CORRECT_ANSWER, CORRECT_ANSWER
}

enum class GameSummaryStatus {
    WON, LOST
}