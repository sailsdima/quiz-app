package com.dp.usecase.impl.score

import com.dp.domain.repository.AppPreferencesRepository
import com.dp.domain.usecase.score.IncreaseUserScoreUseCase
import javax.inject.Inject

class IncreaseUserScoreUseCaseImpl @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository
) : IncreaseUserScoreUseCase {

    override suspend fun increaseUserScoreBy(value: Int) {
        val newUserScore = appPreferencesRepository.getUserScore() + value
        appPreferencesRepository.saveUserScore(newUserScore)
    }
}