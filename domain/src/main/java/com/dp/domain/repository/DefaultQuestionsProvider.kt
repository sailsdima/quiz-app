package com.dp.domain.repository

import com.dp.domain.model.default_data.DefaultQuestions

interface DefaultQuestionsProvider {

    suspend fun getNewQuestions(lastSavedVersion: Int): DefaultQuestions?
}
