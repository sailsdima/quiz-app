package com.dp.data.di

import com.dp.data.repository.DefaultQuestionRepositoryImpl
import com.dp.domain.repository.DefaultQuestionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DefaultDataModule {

    @Binds
    fun bindDefaultQuestionsProvider(provider: DefaultQuestionRepositoryImpl): DefaultQuestionRepository
}