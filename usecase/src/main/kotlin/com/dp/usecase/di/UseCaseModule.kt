package com.dp.usecase.di

import com.dp.domain.usecase.app_preferences.GetMistakesAllowedCountUseCase
import com.dp.domain.usecase.app_preferences.GetQuestionsCountUseCase
import com.dp.domain.usecase.app_preferences.GetSavedQuestionsVersionUseCase
import com.dp.domain.usecase.app_preferences.GetTimePerGameMsUseCase
import com.dp.domain.usecase.app_preferences.GetUserScoreFlowUseCase
import com.dp.domain.usecase.app_preferences.GetUserScoreUseCase
import com.dp.domain.usecase.game.AddQuestionToGameUseCase
import com.dp.domain.usecase.game.AddUserAnswerUseCase
import com.dp.domain.usecase.game.CreateGameSessionUseCase
import com.dp.domain.usecase.game.FinishGameSessionUseCase
import com.dp.domain.usecase.game.GetGameSessionUseCase
import com.dp.domain.usecase.game.StartGameSessionUseCase
import com.dp.domain.usecase.history.GetGamesHistoryUseCase
import com.dp.domain.usecase.question.GetNextRandomQuestionUseCase
import com.dp.domain.usecase.question.IncrementQuestionShownTimesCountUseCase
import com.dp.domain.usecase.score.IncreaseUserScoreUseCase
import com.dp.domain.usecase.synchronize.SynchronizeQuestionsUseCase
import com.dp.usecase.impl.app_preferences.GetMistakesAllowedCountUseCaseImpl
import com.dp.usecase.impl.app_preferences.GetQuestionsCountUseCaseImpl
import com.dp.usecase.impl.app_preferences.GetSavedQuestionsVersionUseCaseImpl
import com.dp.usecase.impl.app_preferences.GetTimePerGameMsUseCaseImpl
import com.dp.usecase.impl.app_preferences.GetUserScoreFlowUseCaseImpl
import com.dp.usecase.impl.app_preferences.GetUserScoreUseCaseImpl
import com.dp.usecase.impl.game.AddQuestionToGameUseCaseImpl
import com.dp.usecase.impl.game.AddUserAnswerUseCaseImpl
import com.dp.usecase.impl.game.CreateGameSessionUseCaseImpl
import com.dp.usecase.impl.game.FinishGameSessionUseCaseImpl
import com.dp.usecase.impl.game.GetGameSessionUseCaseImpl
import com.dp.usecase.impl.game.StartGameSessionUseCaseImpl
import com.dp.usecase.impl.history.GetGamesHistoryUseCaseImpl
import com.dp.usecase.impl.question.GetNextRandomQuestionUseCaseImpl
import com.dp.usecase.impl.question.IncrementQuestionShownTimesCountUseCaseImpl
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
    fun bindGetSavedQuestionsVersionUseCaseImpl(useCase: GetSavedQuestionsVersionUseCaseImpl): GetSavedQuestionsVersionUseCase

    @Binds
    fun bindGetUserScoreUseCase(useCase: GetUserScoreUseCaseImpl): GetUserScoreUseCase

    @Binds
    fun bindGetUserScoreFlowUseCaseImpl(useCase: GetUserScoreFlowUseCaseImpl): GetUserScoreFlowUseCase

    @Binds
    fun bindGetTimePerGameMsUseCaseImpl(useCase: GetTimePerGameMsUseCaseImpl): GetTimePerGameMsUseCase

    @Binds
    fun bindGetQuestionsCountUseCaseImpl(useCase: GetQuestionsCountUseCaseImpl): GetQuestionsCountUseCase

    @Binds
    fun bindGetMistakesAllowedCountUseCaseImpl(useCase: GetMistakesAllowedCountUseCaseImpl): GetMistakesAllowedCountUseCase

    @Binds
    fun bindIncreaseUserScoreUseCase(useCase: IncreaseUserScoreUseCaseImpl): IncreaseUserScoreUseCase

    @Binds
    fun bindAddQuestionToGameUseCase(useCase: AddQuestionToGameUseCaseImpl): AddQuestionToGameUseCase

    @Binds
    fun bindAddUserAnswerUseCase(useCase: AddUserAnswerUseCaseImpl): AddUserAnswerUseCase

    @Binds
    fun bindCreateGameSessionUseCase(useCase: CreateGameSessionUseCaseImpl): CreateGameSessionUseCase

    @Binds
    fun bindGetGameSessionUseCase(useCase: GetGameSessionUseCaseImpl): GetGameSessionUseCase

    @Binds
    fun bindStartGameSessionUseCase(useCase: StartGameSessionUseCaseImpl): StartGameSessionUseCase

    @Binds
    fun bindFinishGameSessionUseCase(useCase: FinishGameSessionUseCaseImpl): FinishGameSessionUseCase

    @Binds
    fun bindGetGamesHistoryUseCase(useCase: GetGamesHistoryUseCaseImpl): GetGamesHistoryUseCase

    @Binds
    fun bindGetNextRandomQuestionUseCase(useCase: GetNextRandomQuestionUseCaseImpl): GetNextRandomQuestionUseCase

    @Binds
    fun bindIncrementQuestionShownTimesCountUseCase(useCase: IncrementQuestionShownTimesCountUseCaseImpl): IncrementQuestionShownTimesCountUseCase

    @Binds
    fun bindSynchronizeQuestionsUseCase(useCase: SynchronizeQuestionsUseCaseImpl): SynchronizeQuestionsUseCase

}