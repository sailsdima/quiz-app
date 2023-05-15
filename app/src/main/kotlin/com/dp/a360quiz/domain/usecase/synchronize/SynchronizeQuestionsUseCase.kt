package com.dp.a360quiz.domain.usecase.synchronize

import android.os.Trace
import com.dp.a360quiz.data.converters.DBEntitiesConverter.toQuestions
import com.dp.a360quiz.data.database.default_data.DefaultQuestionsProvider
import com.dp.a360quiz.domain.model.Question
import com.dp.a360quiz.domain.repository.AppPreferencesRepository
import com.dp.a360quiz.domain.repository.QuestionRepository
import com.dp.a360quiz.domain.time.CurrentTimeProvider
import com.dp.a360quiz.extensions.logi
import kotlinx.coroutines.delay
import javax.inject.Inject

class SynchronizeQuestionsUseCase @Inject constructor(
    private val defaultQuestionsProvider: DefaultQuestionsProvider,
    private val appPreferencesRepository: AppPreferencesRepository,
    private val questionRepository: QuestionRepository,
    private val currentTimeProvider: CurrentTimeProvider,
) {

    suspend fun synchronizeDefaultQuestions() {
        Trace.beginSection("Sync questions")
        val savedQuestionsVersion = appPreferencesRepository.getSavedQuestionsVersion()
        val newQuestions = defaultQuestionsProvider.getNewQuestions(savedQuestionsVersion)

        logi("Saved questions version = $savedQuestionsVersion. Questions version = ${newQuestions?.version}")
delay(100)
        newQuestions?.toQuestions(currentTimeProvider.currentTime)?.let { actualQuestions ->
            removeDeletedQuestions(actualQuestions)
            // remove deleted questions from db
            questionRepository.upsertQuestions(actualQuestions)
            appPreferencesRepository.saveLastQuestionsVersion(newQuestions.version)
        }
        Trace.endSection()
    }

    private suspend fun removeDeletedQuestions(actualQuestions: List<Question>) {
        logi("Actual questions: $actualQuestions")
        val savedQuestionsIds = questionRepository.getSavedQuestionIds()
        logi("Saved questions ids: $savedQuestionsIds")
        val questionIdsToDelete = savedQuestionsIds.subtract(actualQuestions.map { it.id }.toSet())
        logi("Difference to delete ids: $questionIdsToDelete")
        questionRepository.deleteQuestions(questionIdsToDelete)
    }

}