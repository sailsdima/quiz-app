package com.dp.a360quiz.ui.quiz

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dp.a360quiz.R
import com.dp.a360quiz.databinding.FragmentQuizBinding
import com.dp.a360quiz.domain.usecase.AnswerUIEntity
import com.dp.a360quiz.domain.usecase.GameSummaryStatus
import com.dp.a360quiz.domain.usecase.UIAnswerType
import com.dp.a360quiz.domain.usecase.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private lateinit var binding: FragmentQuizBinding

    private val viewModel: QuizViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlayAgain.setOnClickListener { viewModel.restartGame() }
        binding.btnMainMenu.setOnClickListener { findNavController().popBackStack() }
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.eventsFlow.onEach { uiState ->
            when (uiState) {
                is UIState.PendingGame -> binding.handlePendingGameEvent()
                is UIState.PreGameTimerState -> binding.handlePreGameTimerState(uiState)
                is UIState.QuestionState -> binding.handleQuestionState(uiState)
                is UIState.QuestionAnswerSummaryState -> binding.handleQuestionAnswerSummaryState(uiState)
                is UIState.GameSummaryState -> binding.handleGameSummaryState(uiState)
            }
        }.launchIn(lifecycleScope)

        viewModel.gameTimerFlow.onEach {
            binding.tvTimer.text = it.time.toString()
        }.launchIn(lifecycleScope)
    }

    private fun FragmentQuizBinding.handlePendingGameEvent() {
        llPreGame.isVisible = true
        llQuestion.isVisible = false
        llGameResults.isVisible = false
    }

    private fun FragmentQuizBinding.handlePreGameTimerState(preGameTimerState: UIState.PreGameTimerState) {
        llPreGame.isVisible = true
        llQuestion.isVisible = false
        llGameResults.isVisible = false

        tvPreGameTimeLeft.text = preGameTimerState.timeLeft.toString()
        tvPreGameStatus.text = preGameTimerState.message
    }

    private fun FragmentQuizBinding.handleQuestionState(questionState: UIState.QuestionState) {
        llPreGame.isVisible = false
        llQuestion.isVisible = true
        llGameResults.isVisible = false

        val question = questionState.question
        tvQuestion.text = question.question
        btnAnswer1.setupButtonForAnswer(question.id, question.answers[0])
        btnAnswer2.setupButtonForAnswer(question.id, question.answers[1])
        btnAnswer3.setupButtonForAnswer(question.id, question.answers[2])
        btnAnswer4.setupButtonForAnswer(question.id, question.answers[3])

        @SuppressLint("SetTextI18n")
        tvQuestionCounter.text = "${questionState.questionNumber} / ${questionState.questionsCount}"
        tvLivesLeft.text = questionState.livesLeft.toString()
    }

    private fun FragmentQuizBinding.handleQuestionAnswerSummaryState(state: UIState.QuestionAnswerSummaryState) {
        llPreGame.isVisible = false
        llQuestion.isVisible = true
        llGameResults.isVisible = false

        btnAnswer1.setBackgroundColor(state.question.answers[0].answerType)
        btnAnswer2.setBackgroundColor(state.question.answers[1].answerType)
        btnAnswer3.setBackgroundColor(state.question.answers[2].answerType)
        btnAnswer4.setBackgroundColor(state.question.answers[3].answerType)
        btnAnswer1.setOnClickListener(null)
        btnAnswer2.setOnClickListener(null)
        btnAnswer3.setOnClickListener(null)
        btnAnswer4.setOnClickListener(null)
    }

    private fun FragmentQuizBinding.handleGameSummaryState(state: UIState.GameSummaryState) {
        llPreGame.isVisible = false
        llQuestion.isVisible = false
        llGameResults.isVisible = true

        llGameResults.setBackgroundColor(
            if (state.gameSummaryStatus == GameSummaryStatus.WON)
                Color.parseColor("#009688")
            else
                Color.parseColor("#F8BBD0")
        )

        tvGameStatus.text = state.gameSummaryStatus.toString()
        tvScore.text = state.score.toString()
        tvTotalQuestions.text = state.questionsCount.toString()
        tvAnsweredQuestions.text = state.answeredQuestionsCount.toString()
        tvCorrectAnswers.text = state.correctAnswersCount.toString()
        tvWrongAnswers.text = state.wrongAnswersCount.toString()
        tvGameDuration.text = state.gameDurationSec.toString()
    }

    private fun Button.setupButtonForAnswer(questionId: Long, answerEntity: AnswerUIEntity) {
        text = answerEntity.answer
        setBackgroundColor(answerEntity.answerType)
        setOnClickListener { viewModel.answerQuestion(questionId, answerEntity.id) }
    }

    private fun Button.setBackgroundColor(answerType: UIAnswerType) {
        setBackgroundColor(
            when (answerType) {
                UIAnswerType.NOT_ANSWERED -> Color.WHITE
                UIAnswerType.ANSWERED_WRONG -> Color.RED
                UIAnswerType.USER_CORRECT_ANSWER -> Color.parseColor("#00BD00")
                UIAnswerType.CORRECT_ANSWER -> Color.parseColor("#16a116")
            }
        )
    }
}