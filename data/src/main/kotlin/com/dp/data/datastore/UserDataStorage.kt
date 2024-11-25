package com.dp.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import com.dp.data.di.UserDataDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val UNKNOWN_QUESTIONS_VERSION = -1
private const val DEFAULT_USER_SCORE = 0
private const val DEFAULT_TIME_PER_GAME_MS = 20000L
private const val DEFAULT_QUESTIONS_COUNT = 5
private const val DEFAULT_MISTAKES_ALLOWED_COUNT = 2
/**
 * A class responsible for managing user data using [DataStore] for preferences storage.
 * Provides methods to retrieve and store user settings such as score, game time, and question count.
 *
 * @property dataStore The [DataStore] instance used to store user preferences.
 */
class UserDataStorage @Inject constructor(
    @UserDataDataStore private val dataStore: DataStore<Preferences>,
) {

    /**
     * Contains all the keys for storing and retrieving preferences.
     */
    private object PreferencesKey {
        val LAST_QUESTIONS_VERSION = intPreferencesKey(name = "last_questions_version")
        val USER_SCORE = intPreferencesKey(name = "user_score")

        val SETTING_GAME_TIME_PER_GAME_MS = longPreferencesKey(name = "settings_time_per_game")
        val SETTING_GAME_QUESTIONS_COUNT = intPreferencesKey(name = "settings_game_questions_count")
        val SETTING_GAME_MISTAKES_ALLOWED_COUNT =
            intPreferencesKey(name = "settings_game_mistakes_allowed_count")
    }

    /**
     * Retrieves the last saved version of the questions from the preferences.
     * If no version is saved, it returns a default value.
     *
     * @return The last saved questions version, or a default value if not available.
     */
    suspend fun getSavedQuestionsVersion(): Int =
        getPreferencesValue(
            key = PreferencesKey.LAST_QUESTIONS_VERSION,
            defaultValue = UNKNOWN_QUESTIONS_VERSION
        )

    /**
     * Saves the given questions version to the preferences.
     *
     * @param version The version to be saved.
     */
    suspend fun saveLastQuestionsVersion(version: Int) {
        savePreferencesValue(key = PreferencesKey.LAST_QUESTIONS_VERSION, value = version)
    }

    /**
     * Retrieves the user's score from the preferences.
     * If no score is saved, it returns a default value.
     *
     * @return The user's score, or a default value if not available.
     */
    suspend fun getUserScore(): Int =
        getPreferencesValue(key = PreferencesKey.USER_SCORE, defaultValue = DEFAULT_USER_SCORE)

    /**
     * A flow that emits the user's score value from the preferences.
     * The flow will emit the default score value if not previously set.
     */
    val userScoreFlow: Flow<Int> =
        getPreferencesValueFlow(key = PreferencesKey.USER_SCORE, defaultValue = DEFAULT_USER_SCORE)

    /**
     * Saves the user's score to the preferences.
     *
     * @param score The score value to be saved.
     */
    suspend fun saveUserScore(score: Int) {
        savePreferencesValue(key = PreferencesKey.USER_SCORE, value = score)
    }

    /**
     * Retrieves the time per game in milliseconds from the preferences.
     * If no time is set, it returns a default value.
     *
     * @return The time per game in milliseconds, or a default value if not available.
     */
    suspend fun getTimePerGameMs(): Long =
        getPreferencesValue(
            key = PreferencesKey.SETTING_GAME_TIME_PER_GAME_MS,
            defaultValue = DEFAULT_TIME_PER_GAME_MS
        )

    /**
     * Saves the time per game (in milliseconds) to the preferences.
     *
     * @param timeMs The time in milliseconds to be saved.
     */
    suspend fun setTimePerGameMs(timeMs: Long) {
        savePreferencesValue(key = PreferencesKey.SETTING_GAME_TIME_PER_GAME_MS, value = timeMs)
    }

    /**
     * Retrieves the number of questions per game from the preferences.
     * If no count is set, it returns a default value.
     *
     * @return The number of questions per game, or a default value if not available.
     */
    suspend fun getQuestionsCount(): Int =
        getPreferencesValue(
            key = PreferencesKey.SETTING_GAME_QUESTIONS_COUNT,
            defaultValue = DEFAULT_QUESTIONS_COUNT
        )

    /**
     * Saves the number of questions per game to the preferences.
     *
     * @param count The count of questions to be saved.
     */
    suspend fun setQuestionCount(count: Int) {
        savePreferencesValue(key = PreferencesKey.SETTING_GAME_QUESTIONS_COUNT, value = count)
    }

    /**
     * Retrieves the allowed number of mistakes per game from the preferences.
     * If no count is set, it returns a default value.
     *
     * @return The allowed number of mistakes per game, or a default value if not available.
     */
    suspend fun getMistakesAllowedCount(): Int =
        getPreferencesValue(
            key = PreferencesKey.SETTING_GAME_MISTAKES_ALLOWED_COUNT,
            defaultValue = DEFAULT_MISTAKES_ALLOWED_COUNT
        )

    /**
     * Saves the allowed number of mistakes per game to the preferences.
     *
     * @param count The count of allowed mistakes to be saved.
     */
    suspend fun setMistakesAllowedCount(count: Int) {
        savePreferencesValue(
            key = PreferencesKey.SETTING_GAME_MISTAKES_ALLOWED_COUNT,
            value = count
        )
    }

    /**
     * Helper function to save a value to the preferences.
     *
     * @param key The preference key to be used.
     * @param value The value to be saved.
     */
    private suspend fun <T> savePreferencesValue(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    /**
     * Helper function to retrieve a value from the preferences.
     * If the value is not present, the provided default value is returned.
     *
     * @param key The preference key to be used.
     * @param defaultValue The default value to return if the key is not found.
     * @return The value associated with the key, or the default value if not found.
     */
    private suspend fun <T> getPreferencesValue(key: Preferences.Key<T>, defaultValue: T): T =
        getPreferencesValueFlow(key = key, defaultValue = defaultValue).first() ?: defaultValue

    /**
     * Helper function to retrieve a value from the preferences as a flow.
     * If the value is not present, the provided default value is emitted.
     *
     * @param key The preference key to be used.
     * @param defaultValue The default value to emit if the key is not found.
     * @return A flow that emits the value associated with the key, or the default value if not found.
     */
    private fun <T> getPreferencesValueFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        dataStore.data.map { it[key] ?: defaultValue }
}