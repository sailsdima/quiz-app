package com.dp.domain.usecase.score

/**
 * Use case interface for increasing the user's score by a specified value.
 *
 * This use case is responsible for increasing the current score of the user by the given amount.
 */
interface IncreaseUserScoreUseCase {

    /**
     * Increases the user's score by the provided value.
     *
     * @param value The value by which the score should be increased.
     */
    suspend operator fun invoke(value: Int)
}