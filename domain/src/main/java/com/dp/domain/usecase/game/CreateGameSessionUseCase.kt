package com.dp.domain.usecase.game

/**
 * Use case to create a new game session.
 */
fun interface CreateGameSessionUseCase {

    /**
     * Creates a new game session.
     *
     * @return The ID of the newly created game session.
     */
    suspend operator fun invoke(): Long
}