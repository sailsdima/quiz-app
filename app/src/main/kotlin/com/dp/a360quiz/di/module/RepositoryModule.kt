package com.dp.a360quiz.di.module

import com.dp.a360quiz.data.repository.AppPreferencesRepositoryImpl
import com.dp.a360quiz.data.repository.GameSessionRepositoryImpl
import com.dp.a360quiz.data.repository.QuestionRepositoryImpl
import com.dp.a360quiz.domain.repository.AppPreferencesRepository
import com.dp.a360quiz.domain.repository.GameSessionRepository
import com.dp.a360quiz.domain.repository.QuestionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindQuestionRepository(repository: QuestionRepositoryImpl): QuestionRepository

    @Binds
    fun bindGameSessionRepository(repository: GameSessionRepositoryImpl): GameSessionRepository

    @Binds
    fun bindAppPreferencesRepository(repository: AppPreferencesRepositoryImpl): AppPreferencesRepository

}