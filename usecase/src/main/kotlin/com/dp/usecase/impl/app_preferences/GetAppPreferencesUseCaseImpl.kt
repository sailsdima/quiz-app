package com.dp.usecase.impl.app_preferences

import com.dp.domain.repository.UserDataRepository
import com.dp.domain.usecase.app_preferences.GetMistakesAllowedCountUseCase
import com.dp.domain.usecase.app_preferences.GetQuestionsCountUseCase
import com.dp.domain.usecase.app_preferences.GetSavedQuestionsVersionUseCase
import com.dp.domain.usecase.app_preferences.GetTimePerGameMsUseCase
import com.dp.domain.usecase.app_preferences.GetUserScoreFlowUseCase
import com.dp.domain.usecase.app_preferences.GetUserScoreUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of [GetSavedQuestionsVersionUseCase] that fetches the saved questions version
 * from the repository.
 */
class GetSavedQuestionsVersionUseCaseImpl @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : GetSavedQuestionsVersionUseCase {
    /**
     * Retrieves the saved questions version from the repository.
     *
     * @return The saved questions version.
     */
    override suspend fun invoke(): Int = userDataRepository.getSavedQuestionsVersion()
}

/**
 * Implementation of [GetUserScoreUseCase] that fetches the user's score from the repository.
 */
class GetUserScoreUseCaseImpl @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : GetUserScoreUseCase {
    /**
     * Retrieves the user's score from the repository.
     *
     * @return The user's current score.
     */
    override suspend fun invoke(): Int = userDataRepository.getUserScore()
}

/**
 * Implementation of [GetUserScoreFlowUseCase] that fetches the flow of the user's score from
 * the repository.
 */
class GetUserScoreFlowUseCaseImpl @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : GetUserScoreFlowUseCase {
    /**
     * Retrieves the flow of the user's score from the repository.
     *
     * @return A flow representing the user's score.
     */
    override fun invoke(): Flow<Int> = userDataRepository.userScoreFlow
}

/**
 * Implementation of [GetTimePerGameMsUseCase] that fetches the time allowed per game (in
 * milliseconds) from the repository.
 */
class GetTimePerGameMsUseCaseImpl @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : GetTimePerGameMsUseCase {
    /**
     * Retrieves the time allowed per game from the repository.
     *
     * @return The time per game in milliseconds.
     */
    override suspend fun invoke(): Long = userDataRepository.getTimePerGameMs()
}

/**
 * Implementation of [GetQuestionsCountUseCase] that fetches the number of questions available
 * from the repository.
 */
class GetQuestionsCountUseCaseImpl @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : GetQuestionsCountUseCase {
    /**
     * Retrieves the total number of questions from the repository.
     *
     * @return The number of questions.
     */
    override suspend fun invoke(): Int = userDataRepository.getQuestionsCount()
}

/**
 * Implementation of [GetMistakesAllowedCountUseCase] that fetches the number of allowed mistakes
 * from the repository.
 */
class GetMistakesAllowedCountUseCaseImpl @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : GetMistakesAllowedCountUseCase {
    /**
     * Retrieves the number of allowed mistakes from the repository.
     *
     * @return The number of allowed mistakes.
     */
    override suspend fun invoke(): Int = userDataRepository.getMistakesAllowedCount()
}