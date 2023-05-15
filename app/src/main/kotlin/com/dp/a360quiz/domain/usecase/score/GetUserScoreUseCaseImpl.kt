package com.dp.a360quiz.domain.usecase.score

import com.dp.a360quiz.domain.repository.AppPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserScoreUseCaseImpl @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository
) : GetUserScoreUseCase {

    override val userScore: Flow<Int>
        get() = appPreferencesRepository.userScoreFlow

}