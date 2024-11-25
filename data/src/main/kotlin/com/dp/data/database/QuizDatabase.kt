package com.dp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dp.data.database.dao.AnswerDao
import com.dp.data.database.dao.CategoryDao
import com.dp.data.database.dao.GameSessionDao
import com.dp.data.database.dao.QuestionDao
import com.dp.data.database.model.DBAnswer
import com.dp.data.database.model.DBCategory
import com.dp.data.database.model.DBGameSession
import com.dp.data.database.model.DBGameWithQuestionsCrossRef
import com.dp.data.database.model.DBQuestion
import com.dp.data.database.model.DBUserAnswer

@Database(
    version = 2,
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