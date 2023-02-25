package com.dp.a360quiz.domain.usecase.score

import com.dp.a360quiz.domain.repository.AppPreferencesRepository
import javax.inject.Inject

class IncreaseUserScoreUseCaseImpl @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository
) : IncreaseUserScoreUseCase {

    override suspend fun increaseUserScoreBy(value: Int) {
        val newUserScore = appPreferencesRepository.getUserScore() + value
        appPreferencesRepository.saveUserScore(newUserScore)
    }
}