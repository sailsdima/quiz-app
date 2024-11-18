package com.dp.data.di

import com.dp.data.time.CurrentTimeProviderImpl
import com.dp.domain.time.CurrentTimeProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CurrentTimeModule {

    @Binds
    fun bindCurrentTimeProvider(CurrentTimeProvider: CurrentTimeProviderImpl): CurrentTimeProvider
}