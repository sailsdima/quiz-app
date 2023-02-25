package com.dp.a360quiz.di.module

import com.dp.a360quiz.domain.usecase.GameController
import com.dp.a360quiz.domain.usecase.GameControllerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ControllerModule {

    @Binds
    @Singleton
    fun bindGameController(controller: GameControllerImpl): GameController
}