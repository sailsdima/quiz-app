package com.dp.a360quiz.di.module

import com.dp.a360quiz.domain.usecase.app_preferences.GetAppPreferencesUseCase
import com.dp.a360quiz.domain.usecase.app_preferences.GetAppPreferencesUseCaseImpl
import com.dp.a360quiz.domain.usecase.game.*
import com.dp.a360quiz.domain.usecase.history.GetGamesHistoryUseCase
import com.dp.a360quiz.domain.usecase.history.GetGamesHistoryUseCaseImpl
import com.dp.a360quiz.domain.usecase.question.GetNextRandomQuestionUseCase
import com.dp.a360quiz.domain.usecase.question.GetNextRandomQuestionUseCaseImpl
import com.dp.a360quiz.domain.usecase.question.IncrementQuestionShownTimesCountUseCase
import com.dp.a360quiz.domain.usecase.question.IncrementQuestionShownTimesCountUseCaseImpl
import com.dp.a360quiz.domain.usecase.score.GetUserScoreUseCase
import com.dp.a360quiz.domain.usecase.score.GetUserScoreUseCaseImpl
import com.dp.a360quiz.domain.usecase.score.IncreaseUserScoreUseCase
import com.dp.a360quiz.domain.usecase.score.IncreaseUserScoreUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetUserScoreUseCase(userCase: GetUserScoreUseCaseImpl): GetUserScoreUseCase

    @Binds
    fun bindIncreaseUserScoreUseCase(userCase: IncreaseUserScoreUseCaseImpl): IncreaseUserScoreUseCase

    @Binds
    fun bindGetAppPreferencesUseCase(userCase: GetAppPreferencesUseCaseImpl): GetAppPreferencesUseCase

    @Binds
    fun bindAddQuestionToGameUseCase(userCase: AddQuestionToGameUseCaseImpl): AddQuestionToGameUseCase

    @Binds
    fun bindAddUserAnswerUseCase(userCase: AddUserAnswerUseCaseImpl): AddUserAnswerUseCase

    @Binds
    fun bindCreateGameSessionUseCase(userCase: CreateGameSessionUseCaseImpl): CreateGameSessionUseCase

    @Binds
    fun bindGetGameSessionUseCase(userCase: GetGameSessionUseCaseImpl): GetGameSessionUseCase

    @Binds
    fun bindUpdateGameSessionUseCase(userCase: UpdateGameSessionUseCaseImpl): UpdateGameSessionUseCase

    @Binds
    fun bindGetGamesHistoryUseCase(userCase: GetGamesHistoryUseCaseImpl): GetGamesHistoryUseCase

    @Binds
    fun bindGetNextRandomQuestionUseCase(userCase: GetNextRandomQuestionUseCaseImpl): GetNextRandomQuestionUseCase

    @Binds
    fun bindIncrementQuestionShownTimesCountUseCase(userCase: IncrementQuestionShownTimesCountUseCaseImpl): IncrementQuestionShownTimesCountUseCase

}