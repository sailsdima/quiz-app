package com.dp.data.di

import android.content.Context
import androidx.room.Room
import com.dp.data.database.QuizDatabase
import com.dp.data.database.QuizDatabase.Companion.DATABASE_NAME
import com.dp.data.database.dao.AnswerDao
import com.dp.data.database.dao.CategoryDao
import com.dp.data.database.dao.GameSessionDao
import com.dp.data.database.dao.QuestionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideQuizDatabase(@ApplicationContext context: Context): QuizDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = QuizDatabase::class.java,
            name = DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideQuestionDao(quizDatabase: QuizDatabase): QuestionDao {
        return quizDatabase.questionDao()
    }

    @Provides
    @Singleton
    fun provideAnswerDao(quizDatabase: QuizDatabase): AnswerDao {
        return quizDatabase.answerDao()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(quizDatabase: QuizDatabase): CategoryDao {
        return quizDatabase.categoryDao()
    }

    @Provides
    @Singleton
    fun provideGameSessionDao(quizDatabase: QuizDatabase): GameSessionDao {
        return quizDatabase.gameSessionDao()
    }

}