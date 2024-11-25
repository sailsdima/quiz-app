package com.dp.domain.usecase.synchronize

/**
 * Use case for synchronizing questions.
 *
 * This use case fetches the new questions and removes the ones that are no longer present.
 */
fun interface SynchronizeQuestionsUseCase {

    /**
     * Invokes the synchronization process, which updates the questions based on the latest version.
     */
    suspend operator fun invoke()
}