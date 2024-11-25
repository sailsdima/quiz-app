package com.dp.a360quiz.domain.usecase

import com.dp.a360quiz.extensions.logi
import com.dp.domain.model.GameSession
import com.dp.domain.model.answersCount
import com.dp.domain.model.correctAnswersCount
import com.dp.domain.model.getCorrectAnswerId
import com.dp.domain.model.getQuestionById
import com.dp.domain.model.incorrectAnswersCount
import com.dp.domain.model.livesLeft
import com.dp.domain.model.score
import com.dp.domain.model.timer.TimerState
import com.dp.domain.timer.QuizTimer
import com.dp.domain.usecase.game.AddQuestionToGameUseCase
import com.dp.domain.usecase.game.AddUserAnswerUseCase
import com.dp.domain.usecase.game.CreateGameSessionUseCase
import com.dp.domain.usecase.game.FinishGameSessionUseCase
import com.dp.domain.usecase.game.GetGameSessionUseCase
import com.dp.domain.usecase.game.StartGameSessionUseCase
import com.dp.domain.usecase.question.GetNextRandomQuestionUseCase
import com.dp.domain.usecase.question.IncrementQuestionShownTimesCountUseCase
import com.dp.domain.usecase.score.IncreaseUserScoreUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.takeWhile
import javax.inject.Inject
import javax.inject.Provider

private const val PRE_GAME_TIMER_MAX_MS = 3000L
private const val DELAY_BETWEEN_QUESTIONS = 2000L

@OptIn(ExperimentalCoroutinesApi::class)
class GameControllerImpl @Inject constructor(
    private val incrementQuestionShownTimesCountUseCase: IncrementQuestionShownTimesCountUseCase,
    private val getNextRandomQuestionUseCase: GetNextRandomQuestionUseCase,
    private val addQuestionToGameUseCase: AddQuestionToGameUseCase,
    private val addUserAnswerUseCase: AddUserAnswerUseCase,
    private val getGameSessionUseCase: GetGameSessionUseCase,
    private val createGameSessionUseCase: CreateGameSessionUseCase,
    private val startGameSessionUseCase: StartGameSessionUseCase,
    private val finishGameSessionUseCase: FinishGameSessionUseCase,
    private val quizTimerProvider: Provider<QuizTimer>,
    private val increaseUserScoreUseCase: IncreaseUserScoreUseCase,
) : GameController {

    private val supportTimer: QuizTimer by lazy { quizTimerProvider.get() }
    private val gameTimer: QuizTimer by lazy { quizTimerProvider.get() }

    private val _gameSessionIdFlow = MutableSharedFlow<Long>(replay = 1)
    private val gameSessionFlow: Flow<GameSession>
        get() = _gameSessionIdFlow.flatMapLatest { gameSessionId ->
            getGameSessionUseCase(gameSessionId)
        }.onEach { logi("Game session updated $it") }

    private val _gameEventsFlow = MutableStateFlow<UIState>(UIState.PendingGame)
    override val gameEventsFlow: Flow<UIState>
        get() = _gameEventsFlow

    private val _gameTimerFlow = MutableStateFlow<TimerState>(TimerState.Paused(0L))
    override val gameTimerFlow: Flow<TimerState>
        get() = _gameTimerFlow

    override suspend fun createAndStartGame() {
        val gameSessionId = createGameSessionUseCase()
        _gameSessionIdFlow.emit(gameSessionId)

        supportTimer.startTimer(
            PRE_GAME_TIMER_MAX_MS,
            onTick = { timerState ->
                updateUiState(UIState.PreGameTimerState(timerState.time, timerState.getPreGameMessage()))
            },
            doAfter = { startGame(gameSessionId) }
        )
    }

    private suspend fun startGame(gameSessionId: Long) {
        startGameSessionUseCase(gameSessionId)
        val gameDuration = gameSessionFlow.first().timePerGameMs

        nextQuestion()

        gameTimer.startTimer(
            gameDuration,
            onTick = { timerState -> _gameTimerFlow.emit(timerState) },
            doAfter = { finishGame() }
        )
    }

    override suspend fun answerQuestion(questionId: Long, answerId: Long) {
        gameTimer.pause()

        val gameSession = gameSessionFlow.first()
        val question = gameSession.getQuestionById(questionId) ?: return
        val correctAnswerId = question.getCorrectAnswerId()
        addUserAnswerUseCase(gameSession, questionId, answerId)

        supportTimer.startTimer(
            timerTimeMs = DELAY_BETWEEN_QUESTIONS,
            onTick = {
                updateUiState(UIState.QuestionAnswerSummaryState(question.toQuestionUIEntity(answerId, correctAnswerId)))
            },
            doAfter = { nextQuestion() },
        )
    }

    override suspend fun nextQuestion() {
        val gameSession = gameSessionFlow.first()
        if (!gameSession.canShowNextQuestion()) {
            finishGame()
            return
        }
        gameTimer.resume()

        val question = getNextRandomQuestionUseCase()
        addQuestionToGameUseCase(gameSession.id, question.id)
        incrementQuestionShownTimesCountUseCase(question.id)

        val questionsCount = gameSession.questionsCount
        val questionNumber = gameSession.questions.size + 1
        val livesLeft = gameSession.livesLeft()
        updateUiState(UIState.QuestionState(question.toQuestionUIEntity(), questionsCount, questionNumber, livesLeft))
    }

    private suspend fun finishGame() {
        val gameSession = gameSessionFlow.first()

        val questionsCount = gameSession.questionsCount
        val answersCount = gameSession.answersCount()
        val correctAnswersCount = gameSession.correctAnswersCount()
        val mistakesCount = gameSession.incorrectAnswersCount()
        val score = gameSession.score()
        val gameStatus = gameSession.gameSummaryStatus()
        val gameDurationSec = gameTimer.timePassedSec

        updateUiState(
            UIState.GameSummaryState(
                questionsCount,
                score,
                answersCount,
                correctAnswersCount,
                mistakesCount,
                gameDurationSec,
                gameStatus
            )
        )

        finishGameSessionUseCase(gameSession.id, gameDurationSec)
        increaseUserScoreUseCase(score)

        gameTimer.reset()
        supportTimer.reset()
    }

    private suspend fun QuizTimer.startTimer(
        timerTimeMs: Long,
        onTick: suspend (timeLeft: TimerState) -> Unit,
        doAfter: suspend () -> Unit
    ) = coroutineScope {
        reset()
        startTimer(timerTimeMs)
            .takeWhile { it !is TimerState.Finished }
            .onEach { timerState -> onTick(timerState) }
            .onCompletion { doAfter() }
            .launchIn(this)
    }

    private suspend fun updateUiState(state: UIState) {
        _gameEventsFlow.emit(state)
    }

    private fun GameSession.canShowNextQuestion(): Boolean {
        return userAnswers.size < questionsCount && userAnswers.count { !it.isCorrect } <= mistakesAllowedCount
    }

    private fun TimerState.getPreGameMessage(): String = when (time) {
        3L -> "Ready"
        2L -> "Steady"
        else -> "Go"
    }
}