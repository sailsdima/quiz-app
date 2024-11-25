package com.dp.usecase.impl.score

import com.dp.domain.repository.UserDataRepository
import com.dp.domain.usecase.score.IncreaseUserScoreUseCase
import javax.inject.Inject

/**
 * Implementation of [IncreaseUserScoreUseCase].
 *
 * This implementation increases the user's score by the specified value and saves the updated score.
 *
 * @param userDataRepository The repository used to retrieve and save user score.
 */
class IncreaseUserScoreUseCaseImpl @Inject constructor(
    private val userDataRepository: UserDataRepository
) : IncreaseUserScoreUseCase {

    /**
     * Increases the user's score by the specified value.
     * The new score is then saved in the repository.
     *
     * @param value The value to add to the current user's score.
     */
    override suspend fun invoke(value: Int) {
        val newUserScore = userDataRepository.getUserScore() + value
        userDataRepository.saveUserScore(score = newUserScore)
    }
}