package com.dp.a360quiz.data.dao

import com.dp.a360quiz.common.annotation.QuestionType
import com.dp.a360quiz.data.database.model.*
import com.dp.a360quiz.domain.model.GameStatus

object DBModelFactory {

    fun getCategory(id: Long, name: String = "Category $id"): DBCategory {
        return DBCategory(
            id = id,
            name = name
        )
    }

    fun getAnswer(
        id: Long,
        questionId: Long,
        value: String = "Answer",
        isCorrect: Boolean = false
    ): DBAnswer {
        return DBAnswer(
            id = id,
            questionId = questionId,
            value = value,
            isCorrect = isCorrect
        )
    }

    fun getQuestion(
        id: Long,
        categoryId: Long,
        type: QuestionType = QuestionType.FOUR_ANSWERS,
        question: String = "Question",
        difficulty: Int = 1,
        timesShown: Int = 0
    ): DBQuestion {
        return DBQuestion(
            id = id,
            type = type,
            categoryId = categoryId,
            question = question,
            difficulty = difficulty,
            createdAt = 0L,
            updatedAt = 0L,
            timesShown = timesShown
        )
    }

    fun getGameSession(
        id: Long,
        gameStatus: GameStatus = GameStatus.CREATED,
        startedAt: Long? = null,
        finishedAt: Long? = null,
    ): DBGameSession {
        return DBGameSession(
            id = id,
            timePerQuestionMs = 1000L,
            questionsCount = 1,
            mistakesAllowedCount = 3,
            gameStatus = gameStatus,
            createdAt = 0L,
            startedAt = startedAt,
            finishedAt = finishedAt,
        )
    }

    fun getGameWithQuestionCrossRef(
        gameId: Long,
        questionId: Long
    ): DBGameWithQuestionsCrossRef {
        return DBGameWithQuestionsCrossRef(
            gameId = gameId,
            questionId = questionId
        )
    }
}