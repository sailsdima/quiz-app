package com.dp.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    /**
     * Retrieves the version of the last saved questions.
     *
     * @return The saved version of the questions, or a default value if not set.
     */
    suspend fun getSavedQuestionsVersion(): Int

    /**
     * Saves the current version of the questions.
     *
     * @param version The version number to save.
     */
    suspend fun saveLastQuestionsVersion(version: Int)

    /**
     * Retrieves the user score as a reactive flow.
     *
     * @return A Flow of the user score.
     */
    val userScoreFlow: Flow<Int>

    /**
     * Retrieves the user score.
     *
     * @return The user score.
     */
    suspend fun getUserScore(): Int

    /**
     * Saves the user score.
     *
     * @param score The score to save.
     */
    suspend fun saveUserScore(score: Int)

    /**
     * Retrieves the time per game in milliseconds.
     *
     * @return The time per game in milliseconds.
     */
    suspend fun getTimePerGameMs(): Long

    /**
     * Saves the time per game in milliseconds.
     *
     * @param timeMs The time in milliseconds to save.
     */
    suspend fun setTimePerGameMs(timeMs: Long)

    /**
     * Retrieves the number of questions per game.
     *
     * @return The number of questions per game.
     */
    suspend fun getQuestionsCount(): Int

    /**
     * Sets the number of questions per game.
     *
     * @param count The number of questions to set.
     */
    suspend fun setQuestionCount(count: Int)

    /**
     * Retrieves the allowed number of mistakes per game.
     *
     * @return The allowed number of mistakes.
     */
    suspend fun getMistakesAllowedCount(): Int

    /**
     * Sets the allowed number of mistakes per game.
     *
     * @param count The number of allowed mistakes.
     */
    suspend fun setMistakesAllowedCount(count: Int)
}