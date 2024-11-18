package com.dp.usecase.impl.score

import com.dp.domain.repository.AppPreferencesRepository
import com.dp.domain.usecase.score.GetUserScoreUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserScoreUseCaseImpl @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository
) : GetUserScoreUseCase {

    override val userScore: Flow<Int>
        get() = appPreferencesRepository.userScoreFlow

}