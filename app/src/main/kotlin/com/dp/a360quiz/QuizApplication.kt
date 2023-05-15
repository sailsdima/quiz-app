package com.dp.a360quiz

import android.app.Application
import android.content.Context
import com.dp.a360quiz.domain.usecase.synchronize.SynchronizeQuestionsUseCase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
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
        Timber.d("onCreate")

        scope.launch {
            synchronizeQuestionsUseCase.synchronizeDefaultQuestions()
        }
    }

    override fun attachBaseContext(base: Context) {
        Timber.d("attachBaseContext")
        super.attachBaseContext(LocaleUtil.getLocalizedContext(base, "en"))
    }
}