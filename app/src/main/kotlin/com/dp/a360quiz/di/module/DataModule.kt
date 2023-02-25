package com.dp.a360quiz.di.module

import com.dp.a360quiz.data.database.default_data.DefaultQuestionsProvider
import com.dp.a360quiz.data.database.default_data.DefaultQuestionsProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindDefaultQuestionsProvider(provider: DefaultQuestionsProviderImpl): DefaultQuestionsProvider
}