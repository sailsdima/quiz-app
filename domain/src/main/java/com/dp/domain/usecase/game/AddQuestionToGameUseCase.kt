package com.dp.domain.usecase.game

/**
 * Use case to add a question to a game session.
 */
fun interface AddQuestionToGameUseCase {

    /**
     * Adds a question to a specified game session.
     *
     * @param gameSessionId The ID of the game session.
     * @param questionId The ID of the question to add.
     */
    suspend operator fun invoke(gameSessionId: Long, questionId: Long)
}