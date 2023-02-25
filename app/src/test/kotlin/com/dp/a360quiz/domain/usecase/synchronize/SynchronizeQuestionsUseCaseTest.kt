package com.dp.a360quiz.domain.usecase.synchronize

import com.dp.a360quiz.data.database.default_data.DefaultQuestionsProvider
import com.dp.a360quiz.domain.repository.AppPreferencesRepository
import com.dp.a360quiz.domain.repository.QuestionRepository
import com.dp.a360quiz.domain.time.CurrentTimeProvider
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.inspectors.runTest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

class SynchronizeQuestionsUseCaseTest : AnnotationSpec() {

    @MockK
    private lateinit var defaultQuestionsProvider: DefaultQuestionsProvider

    @MockK
    private lateinit var appPreferencesRepository: AppPreferencesRepository

    @MockK
    private lateinit var questionRepository: QuestionRepository

    @MockK
    private lateinit var currentTimeProvider: CurrentTimeProvider

    private lateinit var synchronizeQuestionsUseCase: SynchronizeQuestionsUseCase

    @BeforeEach
    fun setup() {
        synchronizeQuestionsUseCase = SynchronizeQuestionsUseCase(
            defaultQuestionsProvider,
            appPreferencesRepository,
            questionRepository,
            currentTimeProvider
        )
    }

}