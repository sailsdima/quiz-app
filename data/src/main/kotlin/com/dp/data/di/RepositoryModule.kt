package com.dp.data.di

import com.dp.data.repository.UserDataRepositoryImpl
import com.dp.data.repository.GameSessionRepositoryImpl
import com.dp.data.repository.QuestionRepositoryImpl
import com.dp.domain.repository.UserDataRepository
import com.dp.domain.repository.GameSessionRepository
import com.dp.domain.repository.QuestionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindQuestionRepository(repository: QuestionRepositoryImpl): QuestionRepository

    @Binds
    @Singleton
    fun bindGameSessionRepository(repository: GameSessionRepositoryImpl): GameSessionRepository

    @Binds
    @Singleton
    fun bindAppPreferencesRepository(repository: UserDataRepositoryImpl): UserDataRepository

}