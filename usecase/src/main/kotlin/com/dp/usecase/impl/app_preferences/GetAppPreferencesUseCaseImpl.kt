package com.dp.usecase.impl.app_preferences

import com.dp.domain.repository.AppPreferencesRepository
import com.dp.domain.usecase.app_preferences.GetAppPreferencesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppPreferencesUseCaseImpl @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository
) : GetAppPreferencesUseCase {

    override val userScoreFlow: Flow<Int>
        get() = appPreferencesRepository.userScoreFlow

    override suspend fun getSavedQuestionsVersion(): Int = appPreferencesRepository.getSavedQuestionsVersion()

    override suspend fun getUserScore(): Int = appPreferencesRepository.getUserScore()

    override suspend fun getTimePerGameMs(): Long = appPreferencesRepository.getTimePerGameMs()

    override suspend fun getQuestionsCount(): Int = appPreferencesRepository.getQuestionsCount()

    override suspend fun getMistakesAllowedCount(): Int = appPreferencesRepository.getMistakesAllowedCount()
}