package com.dp.a360quiz.domain.usecase

import com.dp.a360quiz.data.converters.DBEntitiesConverter.toQuestionUIEntity
import com.dp.a360quiz.domain.model.*
import com.dp.a360quiz.domain.time.QuizTimer
import com.dp.a360quiz.domain.time.TimerState
import com.dp.a360quiz.domain.usecase.game.*
import com.dp.a360quiz.domain.usecase.question.GetNextRandomQuestionUseCase
import com.dp.a360quiz.domain.usecase.question.IncrementQuestionShownTimesCountUseCase
import com.dp.a360quiz.domain.usecase.score.IncreaseUserScoreUseCase
import com.dp.a360quiz.extensions.logi
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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
    private val updateGameSessionUseCase: UpdateGameSessionUseCase,
    private val createGameSessionUseCase: CreateGameSessionUseCase,
    private val quizTimerProvider: Provider<QuizTimer>,
    private val increaseUserScoreUseCase: IncreaseUserScoreUseCase,
) : GameController {

    private val supportTimer: QuizTimer by lazy { quizTimerProvider.get() }
    private val gameTimer: QuizTimer by lazy { quizTimerProvider.get() }

    private val _gameSessionIdFlow = MutableSharedFlow<Long>(replay = 1)
    private val gameSessionFlow: Flow<GameSession>
        get() = _gameSessionIdFlow.flatMapLatest { gameSessionId ->
            getGameSessionUseCase.execute(gameSessionId)
        }.onEach { logi("Game session updated $it") }

    private val _gameEventsFlow = MutableStateFlow<UIState>(UIState.PendingGame)
    override val gameEventsFlow: Flow<UIState>
        get() = _gameEventsFlow

    private val _gameTimerFlow = MutableStateFlow<TimerState>(TimerState.Paused(0L))
    override val gameTimerFlow: Flow<TimerState>
        get() = _gameTimerFlow

    override suspend fun createAndStartGame() {
        val gameSessionId = createGameSessionUseCase.execute()
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
        updateGameSessionUseCase.startGame(gameSessionId)
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
        addUserAnswerUseCase.execute(gameSession, questionId, answerId)

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

        val question = getNextRandomQuestionUseCase.execute()
        addQuestionToGameUseCase.execute(gameSession.id, question.id)
        incrementQuestionShownTimesCountUseCase.execute(question.id)

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

        updateGameSessionUseCase.finishGame(gameSession.id, gameDurationSec)
        increaseUserScoreUseCase.increaseUserScoreBy(score)

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