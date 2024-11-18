package com.dp.domain.usecase.app_preferences

import kotlinx.coroutines.flow.Flow

interface GetAppPreferencesUseCase {

    val userScoreFlow: Flow<Int>

    suspend fun getSavedQuestionsVersion(): Int

    suspend fun getUserScore(): Int

    suspend fun getTimePerGameMs(): Long

    suspend fun getQuestionsCount(): Int

    suspend fun getMistakesAllowedCount(): Int
}