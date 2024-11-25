package com.dp.a360quiz.ui.main

import androidx.lifecycle.ViewModel
import com.dp.domain.usecase.app_preferences.GetUserScoreFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getUserScoreFlowUseCase: GetUserScoreFlowUseCase,
) : ViewModel() {

    val currentScoreFlow = getUserScoreFlowUseCase()

}