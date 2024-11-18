package com.dp.a360quiz

import android.app.Application
import com.dp.domain.usecase.synchronize.SynchronizeQuestionsUseCase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import timber.log.Timber
import timber.log.Timber.Forest.plant
import javax.inject.Inject


@HiltAndroidApp
class QuizApplication : Application() {

    @Inject
    lateinit var synchronizeQuestionsUseCase: SynchronizeQuestionsUseCase

    private val scope = MainScope()

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }

        scope.launch {
            synchronizeQuestionsUseCase()
        }
    }

}