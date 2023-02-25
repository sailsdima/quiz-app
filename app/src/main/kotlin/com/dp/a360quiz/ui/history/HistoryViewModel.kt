package com.dp.a360quiz.ui.history

import androidx.lifecycle.ViewModel
import com.dp.a360quiz.domain.model.GameSession
import com.dp.a360quiz.domain.usecase.history.GetGamesHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getGamesHistoryUseCase: GetGamesHistoryUseCase
) : ViewModel() {

    val gameHistoryFlow: Flow<List<GameSession>>
        get() = getGamesHistoryUseCase.gameHistory

}