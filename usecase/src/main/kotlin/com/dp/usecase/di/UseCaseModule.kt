package com.dp.usecase.di

import com.dp.domain.usecase.app_preferences.GetAppPreferencesUseCase
import com.dp.domain.usecase.game.AddQuestionToGameUseCase
import com.dp.domain.usecase.game.AddUserAnswerUseCase
import com.dp.domain.usecase.game.CreateGameSessionUseCase
import com.dp.domain.usecase.game.GetGameSessionUseCase
import com.dp.domain.usecase.game.UpdateGameSessionUseCase
import com.dp.domain.usecase.history.GetGamesHistoryUseCase
import com.dp.domain.usecase.question.GetNextRandomQuestionUseCase
import com.dp.domain.usecase.question.IncrementQuestionShownTimesCountUseCase
import com.dp.domain.usecase.score.GetUserScoreUseCase
import com.dp.domain.usecase.score.IncreaseUserScoreUseCase
import com.dp.domain.usecase.synchronize.SynchronizeQuestionsUseCase
import com.dp.usecase.impl.app_preferences.GetAppPreferencesUseCaseImpl
import com.dp.usecase.impl.game.AddQuestionToGameUseCaseImpl
import com.dp.usecase.impl.game.AddUserAnswerUseCaseImpl
import com.dp.usecase.impl.game.CreateGameSessionUseCaseImpl
import com.dp.usecase.impl.game.GetGameSessionUseCaseImpl
import com.dp.usecase.impl.game.UpdateGameSessionUseCaseImpl
import com.dp.usecase.impl.history.GetGamesHistoryUseCaseImpl
import com.dp.usecase.impl.question.GetNextRandomQuestionUseCaseImpl
import com.dp.usecase.impl.question.IncrementQuestionShownTimesCountUseCaseImpl
import com.dp.usecase.impl.score.GetUserScoreUseCaseImpl
import com.dp.usecase.impl.score.IncreaseUserScoreUseCaseImpl
import com.dp.usecase.impl.synchronize.SynchronizeQuestionsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetUserScoreUseCase(useCase: GetUserScoreUseCaseImpl): GetUserScoreUseCase

    @Binds
    fun bindIncreaseUserScoreUseCase(useCase: IncreaseUserScoreUseCaseImpl): IncreaseUserScoreUseCase

    @Binds
    fun bindGetAppPreferencesUseCase(useCase: GetAppPreferencesUseCaseImpl): GetAppPreferencesUseCase

    @Binds
    fun bindAddQuestionToGameUseCase(useCase: AddQuestionToGameUseCaseImpl): AddQuestionToGameUseCase

    @Binds
    fun bindAddUserAnswerUseCase(useCase: AddUserAnswerUseCaseImpl): AddUserAnswerUseCase

    @Binds
    fun bindCreateGameSessionUseCase(useCase: CreateGameSessionUseCaseImpl): CreateGameSessionUseCase

    @Binds
    fun bindGetGameSessionUseCase(useCase: GetGameSessionUseCaseImpl): GetGameSessionUseCase

    @Binds
    fun bindUpdateGameSessionUseCase(useCase: UpdateGameSessionUseCaseImpl): UpdateGameSessionUseCase

    @Binds
    fun bindGetGamesHistoryUseCase(useCase: GetGamesHistoryUseCaseImpl): GetGamesHistoryUseCase

    @Binds
    fun bindGetNextRandomQuestionUseCase(useCase: GetNextRandomQuestionUseCaseImpl): GetNextRandomQuestionUseCase

    @Binds
    fun bindIncrementQuestionShownTimesCountUseCase(useCase: IncrementQuestionShownTimesCountUseCaseImpl): IncrementQuestionShownTimesCountUseCase

    @Binds
    fun bindSynchronizeQuestionsUseCase(useCase: SynchronizeQuestionsUseCaseImpl): SynchronizeQuestionsUseCase

}