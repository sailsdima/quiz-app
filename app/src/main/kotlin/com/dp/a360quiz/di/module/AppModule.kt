package com.dp.a360quiz.di.module

import com.dp.a360quiz.data.time.CurrentTimeProviderImpl
import com.dp.a360quiz.domain.time.CurrentTimeProvider
import com.dp.a360quiz.domain.time.QuizTimer
import com.dp.a360quiz.domain.time.QuizTimerImpl
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun bindCurrentTimeProvider(provider: CurrentTimeProviderImpl): CurrentTimeProvider

    @Binds
    fun bindQuizTimer(timer: QuizTimerImpl): QuizTimer

}

@Module
@InstallIn(SingletonComponent::class)
object AppBindingModule {

    @Provides
    @Singleton
    fun provideGson() = Gson()
}