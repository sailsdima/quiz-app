package com.dp.a360quiz.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dp.a360quiz.data.database.dao.AnswerDao
import com.dp.a360quiz.data.database.dao.CategoryDao
import com.dp.a360quiz.data.database.dao.GameSessionDao
import com.dp.a360quiz.data.database.dao.QuestionDao
import com.dp.a360quiz.data.database.model.*

@Database(
    version = 1,
    entities = [
        DBQuestion::class,
        DBCategory::class,
        DBAnswer::class,
        DBGameSession::class,
        DBUserAnswer::class,
        DBGameWithQuestionsCrossRef::class
    ],
)
abstract class QuizDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDao

    abstract fun answerDao(): AnswerDao

    abstract fun gameSessionDao(): GameSessionDao

    abstract fun categoryDao(): CategoryDao

    companion object {
        const val DATABASE_NAME = "quiz360_db"
    }
}