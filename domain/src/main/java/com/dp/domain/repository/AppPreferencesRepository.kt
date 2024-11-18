package com.dp.domain.repository

import kotlinx.coroutines.flow.Flow

interface AppPreferencesRepository {

    suspend fun getSavedQuestionsVersion(): Int
    suspend fun saveLastQuestionsVersion(version: Int)

    val userScoreFlow: Flow<Int>
    suspend fun getUserScore(): Int
    suspend fun saveUserScore(score: Int)

    suspend fun getTimePerGameMs(): Long
    suspend fun setTimePerGameMs(timeMs: Long)

    suspend fun getQuestionsCount(): Int
    suspend fun setQuestionCount(count: Int)

    suspend fun getMistakesAllowedCount(): Int
    suspend fun setMistakesAllowedCount(count: Int)

}