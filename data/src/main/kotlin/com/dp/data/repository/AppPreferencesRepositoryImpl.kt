package com.dp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import com.dp.domain.repository.AppPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val UNKNOWN_QUESTIONS_VERSION = -1
private const val DEFAULT_USER_SCORE = 0
private const val DEFAULT_TIME_PER_GAME_MS = 20000L
private const val DEFAULT_QUESTIONS_COUNT = 5
private const val DEFAULT_MISTAKES_ALLOWED_COUNT = 2

class AppPreferencesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AppPreferencesRepository {

    private object PreferencesKey {
        val LAST_QUESTIONS_VERSION = intPreferencesKey("last_questions_version")
        val USER_SCORE = intPreferencesKey("user_score")

        val SETTING_GAME_TIME_PER_GAME_MS = longPreferencesKey("settings_time_per_game")
        val SETTING_GAME_QUESTIONS_COUNT = intPreferencesKey("settings_game_questions_count")
        val SETTING_GAME_MISTAKES_ALLOWED_COUNT = intPreferencesKey("settings_game_mistakes_allowed_count")
    }

    override suspend fun getSavedQuestionsVersion(): Int =
        getPreferencesValue(PreferencesKey.LAST_QUESTIONS_VERSION, UNKNOWN_QUESTIONS_VERSION)


    override suspend fun saveLastQuestionsVersion(version: Int) {
        savePreferencesValue(PreferencesKey.LAST_QUESTIONS_VERSION, version)
    }

    override suspend fun getUserScore(): Int = getPreferencesValue(PreferencesKey.USER_SCORE, DEFAULT_USER_SCORE)

    override val userScoreFlow: Flow<Int> =
        getPreferencesValueFlow(PreferencesKey.USER_SCORE, DEFAULT_USER_SCORE)

    override suspend fun saveUserScore(score: Int) {
        savePreferencesValue(PreferencesKey.USER_SCORE, score)
    }

    override suspend fun getTimePerGameMs(): Long =
        getPreferencesValue(PreferencesKey.SETTING_GAME_TIME_PER_GAME_MS, DEFAULT_TIME_PER_GAME_MS)


    override suspend fun setTimePerGameMs(timeMs: Long) {
        savePreferencesValue(PreferencesKey.SETTING_GAME_TIME_PER_GAME_MS, timeMs)
    }

    override suspend fun getQuestionsCount(): Int =
        getPreferencesValue(PreferencesKey.SETTING_GAME_QUESTIONS_COUNT, DEFAULT_QUESTIONS_COUNT)


    override suspend fun setQuestionCount(count: Int) {
        savePreferencesValue(PreferencesKey.SETTING_GAME_QUESTIONS_COUNT, count)
    }

    override suspend fun getMistakesAllowedCount(): Int =
        getPreferencesValue(PreferencesKey.SETTING_GAME_MISTAKES_ALLOWED_COUNT, DEFAULT_MISTAKES_ALLOWED_COUNT)


    override suspend fun setMistakesAllowedCount(count: Int) {
        savePreferencesValue(PreferencesKey.SETTING_GAME_MISTAKES_ALLOWED_COUNT, count)
    }


    private suspend fun <T> savePreferencesValue(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    private suspend fun <T> getPreferencesValue(key: Preferences.Key<T>, defaultValue: T): T =
        getPreferencesValueFlow(key, defaultValue).first() ?: defaultValue

    private fun <T> getPreferencesValueFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        dataStore.data.map { it[key] ?: defaultValue }


}