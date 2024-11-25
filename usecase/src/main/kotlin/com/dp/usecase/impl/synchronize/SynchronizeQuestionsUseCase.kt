package com.dp.usecase.impl.synchronize

import com.dp.domain.model.Question
import com.dp.domain.repository.AppPreferencesRepository
import com.dp.domain.repository.DefaultQuestionsProvider
import com.dp.domain.repository.QuestionRepository
import com.dp.domain.usecase.synchronize.SynchronizeQuestionsUseCase
import javax.inject.Inject

/**
 * Implementation of [SynchronizeQuestionsUseCase].
 *
 * This implementation retrieves the current questions from the provider, compares them with
 * the saved questions, and updates the database with any new or removed questions.
 *
 * @param defaultQuestionsProvider The provider of the default questions to be synchronized.
 * @param appPreferencesRepository Repository for storing app preferences, such as saved questions version.
 * @param questionRepository Repository responsible for saving, updating, and deleting questions.
 */
class SynchronizeQuestionsUseCaseImpl @Inject constructor(
    private val defaultQuestionsProvider: DefaultQuestionsProvider,
    private val appPreferencesRepository: AppPreferencesRepository,
    private val questionRepository: QuestionRepository
) : SynchronizeQuestionsUseCase {

    /**
     * Synchronizes the questions by comparing the saved questions version with the new questions.
     * Updates the questions in the database and removes deleted ones.
     */
    override suspend fun invoke() {
        // Get the saved questions version from preferences
        val savedQuestionsVersion = appPreferencesRepository.getSavedQuestionsVersion()

        // Fetch new questions based on the saved version
        val newQuestions = defaultQuestionsProvider.getNewQuestions(savedQuestionsVersion)

        newQuestions?.questions?.let { actualQuestions ->
            // Remove deleted questions from the database
            removeDeletedQuestions(actualQuestions)

            // Update or insert the new questions in the repository
            questionRepository.upsertQuestions(actualQuestions)

            // Save the new version number to preferences
            appPreferencesRepository.saveLastQuestionsVersion(newQuestions.version)
        }
    }

    /**
     * Removes questions that no longer exist in the provided list of actual questions.
     *
     * @param actualQuestions The list of questions that should be kept.
     */
    private suspend fun removeDeletedQuestions(actualQuestions: List<Question>) {
        // Get the IDs of the questions currently saved in the repository
        val savedQuestionsIds = questionRepository.getSavedQuestionIds()

        // Find the IDs of questions to delete (those not present in the actual list)
        val questionIdsToDelete = savedQuestionsIds.subtract(actualQuestions.map { it.id }.toSet())

        // Delete the questions that are no longer present
        questionRepository.deleteQuestions(questionIdsToDelete)
    }
}