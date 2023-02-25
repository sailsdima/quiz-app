package com.dp.a360quiz.data.database.default_data

interface DefaultQuestionsProvider {

    suspend fun getNewQuestions(lastSavedVersion: Int): DefaultQuestions?
}
