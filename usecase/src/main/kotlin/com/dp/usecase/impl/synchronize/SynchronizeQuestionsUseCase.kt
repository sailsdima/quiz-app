package com.dp.usecase.impl.synchronize

import com.dp.domain.model.Question
import com.dp.domain.repository.AppPreferencesRepository
import com.dp.domain.repository.DefaultQuestionsProvider
import com.dp.domain.repository.QuestionRepository
import com.dp.domain.usecase.synchronize.SynchronizeQuestionsUseCase
import javax.inject.Inject

class SynchronizeQuestionsUseCaseImpl @Inject constructor(
    private val defaultQuestionsProvider: DefaultQuestionsProvider,
    private val appPreferencesRepository: AppPreferencesRepository,
    private val questionRepository: QuestionRepository,
) : SynchronizeQuestionsUseCase {

    override suspend fun invoke() {
        val savedQuestionsVersion = appPreferencesRepository.getSavedQuestionsVersion()
        val newQuestions = defaultQuestionsProvider.getNewQuestions(savedQuestionsVersion)

//        logi("Saved questions version = $savedQuestionsVersion. Questions version = ${newQuestions?.version}")

        newQuestions?.questions?.let { actualQuestions ->
            removeDeletedQuestions(actualQuestions)
            // remove deleted questions from db
            questionRepository.upsertQuestions(actualQuestions)
            appPreferencesRepository.saveLastQuestionsVersion(newQuestions.version)
        }
    }

    private suspend fun removeDeletedQuestions(actualQuestions: List<Question>) {
//        logi("Actual questions: $actualQuestions")
        val savedQuestionsIds = questionRepository.getSavedQuestionIds()
//        logi("Saved questions ids: $savedQuestionsIds")
        val questionIdsToDelete = savedQuestionsIds.subtract(actualQuestions.map { it.id }.toSet())
//        logi("Difference to delete ids: $questionIdsToDelete")
        questionRepository.deleteQuestions(questionIdsToDelete)
    }

}