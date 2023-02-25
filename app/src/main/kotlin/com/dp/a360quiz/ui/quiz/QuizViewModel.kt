package com.dp.a360quiz.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dp.a360quiz.domain.usecase.GameController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val gameController: GameController
) : ViewModel() {

    val eventsFlow = gameController.gameEventsFlow
    val gameTimerFlow = gameController.gameTimerFlow

    init {
        restartGame()
    }

    fun restartGame() {
        viewModelScope.launch {
            gameController.createAndStartGame()
        }
    }

    fun answerQuestion(questionId: Long, answerId: Long) {
        viewModelScope.launch {
            gameController.answerQuestion(questionId, answerId)
        }
    }

}