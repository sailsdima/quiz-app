package com.dp.data.di

import com.dp.data.timer.QuizTimerImpl
import com.dp.domain.timer.QuizTimer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TimerModule {

    @Binds
    fun bindQuizTimer(provider: QuizTimerImpl): QuizTimer
}