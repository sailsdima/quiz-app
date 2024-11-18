package com.dp.a360quiz.ui.main

import androidx.lifecycle.ViewModel
import com.dp.domain.usecase.score.GetUserScoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getUserScoreUseCase: GetUserScoreUseCase
) : ViewModel() {

    val currentScoreFlow = getUserScoreUseCase.userScore

}