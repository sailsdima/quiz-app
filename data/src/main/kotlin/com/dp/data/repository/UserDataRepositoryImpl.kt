package com.dp.data.repository

import com.dp.data.datastore.UserDataStorage
import com.dp.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of the [UserDataRepository] interface that interacts with [UserDataStorage]
 * to manage user-related data in the application.
 *
 * @param storage The [UserDataStorage] instance responsible for reading and writing data.
 */
class UserDataRepositoryImpl @Inject constructor(
    private val storage: UserDataStorage,
) : UserDataRepository {

    /**
     * Retrieves the saved version of the questions.
     *
     * @return The saved version of the questions, or a default value if not set.
     */
    override suspend fun getSavedQuestionsVersion(): Int = storage.getSavedQuestionsVersion()

    /**
     * Saves the version of the questions.
     *
     * @param version The version of the questions to save.
     */
    override suspend fun saveLastQuestionsVersion(version: Int) =
        storage.saveLastQuestionsVersion(version = version)

    /**
     * Retrieves the user's score.
     *
     * @return The current user score.
     */
    override suspend fun getUserScore(): Int = storage.getUserScore()

    /**
     * A reactive stream that emits the user's score.
     *
     * @return A Flow of the user score.
     */
    override val userScoreFlow: Flow<Int> = storage.userScoreFlow

    /**
     * Saves the user's score.
     *
     * @param score The score to save.
     */
    override suspend fun saveUserScore(score: Int) = storage.saveUserScore(score = score)

    /**
     * Retrieves the time allocated for a single game in milliseconds.
     *
     * @return The time per game in milliseconds.
     */
    override suspend fun getTimePerGameMs(): Long = storage.getTimePerGameMs()

    /**
     * Saves the time allocated for a single game in milliseconds.
     *
     * @param timeMs The time in milliseconds to save.
     */
    override suspend fun setTimePerGameMs(timeMs: Long) = storage.setTimePerGameMs(timeMs = timeMs)

    /**
     * Retrieves the number of questions per game.
     *
     * @return The number of questions per game.
     */
    override suspend fun getQuestionsCount(): Int = storage.getQuestionsCount()

    /**
     * Sets the number of questions per game.
     *
     * @param count The number of questions to set.
     */
    override suspend fun setQuestionCount(count: Int) = storage.setQuestionCount(count = count)

    /**
     * Retrieves the number of allowed mistakes per game.
     *
     * @return The number of allowed mistakes.
     */
    override suspend fun getMistakesAllowedCount(): Int = storage.getMistakesAllowedCount()

    /**
     * Sets the number of allowed mistakes per game.
     *
     * @param count The number of mistakes to set.
     */
    override suspend fun setMistakesAllowedCount(count: Int) =
        storage.setMistakesAllowedCount(count = count)
}