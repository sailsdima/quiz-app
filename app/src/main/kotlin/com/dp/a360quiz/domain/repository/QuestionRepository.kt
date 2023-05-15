package com.dp.a360quiz.domain.repository

import com.dp.a360quiz.domain.model.Question

interface QuestionRepository {

    suspend fun upsertQuestions(questions: List<Question>)

    suspend fun getSavedQuestionIds(): List<Long>

    suspend fun deleteQuestions(questionIds: Set<Long>)

    suspend fun getQuestionById(questionId: Long): Question

    suspend fun getNextRandomQuestion(): Question

    suspend fun incrementQuestionShownTimesCount(questionId: Long)

    suspend fun clearQuestions()

}