package com.dp.data.di

import com.dp.data.default_data.DefaultQuestionsProviderImpl
import com.dp.domain.repository.DefaultQuestionsProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DefaultDataModule {

    @Binds
    fun bindDefaultQuestionsProvider(provider: DefaultQuestionsProviderImpl): DefaultQuestionsProvider
}