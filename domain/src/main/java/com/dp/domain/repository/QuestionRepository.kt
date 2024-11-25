package com.dp.domain.repository

import com.dp.domain.model.Question

/**
 * Repository for managing question data.
 */
interface QuestionRepository {

    /**
     * Upserts a list of questions.
     *
     * @param questions The list of questions to upsert.
     */
    suspend fun upsertQuestions(questions: List<Question>)

    /**
     * Fetches a list of IDs of saved questions.
     *
     * @return A list of question IDs.
     */
    suspend fun getSavedQuestionIds(): List<Long>

    /**
     * Deletes questions with the specified IDs.
     *
     * @param questionIds A set of question IDs to delete.
     */
    suspend fun deleteQuestions(questionIds: Set<Long>)

    /**
     * Fetches a question by its ID.
     *
     * @param questionId The ID of the question to fetch.
     * @return The requested question.
     */
    suspend fun getQuestionById(questionId: Long): Question

    /**
     * Fetches a random question.
     *
     * @return A random question.
     */
    suspend fun getNextRandomQuestion(): Question

    /**
     * Increments the count of how many times a question has been shown.
     *
     * @param questionId The ID of the question to increment the count for.
     */
    suspend fun incrementQuestionShownTimesCount(questionId: Long)

    /**
     * Clears all saved questions.
     */
    suspend fun clearQuestions()
}