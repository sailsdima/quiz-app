package com.dp.domain.repository

import com.dp.domain.model.default_data.DefaultQuestions

/**
 * Repository for managing default questions.
 */
interface DefaultQuestionRepository {

    /**
     * Fetches new questions if available, based on the last saved version.
     *
     * @param lastSavedVersion The version of questions previously saved.
     * @return DefaultQuestions object if new questions are available, null otherwise.
     */
    suspend fun fetchUpdatedQuestionsIfAvailable(lastSavedVersion: Int): DefaultQuestions?
}