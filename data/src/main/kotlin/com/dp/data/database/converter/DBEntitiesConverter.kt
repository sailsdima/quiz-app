package com.dp.data.database.converter

import com.dp.data.database.model.DBAnswer
import com.dp.data.database.model.DBCategory
import com.dp.data.database.model.DBGameSession
import com.dp.data.database.model.DBQuestion
import com.dp.data.database.model.DBUserAnswer
import com.dp.data.database.model.GameWithQuestions
import com.dp.data.database.model.QuestionWithAnswers
import com.dp.data.default_data.model.GDefaultQuestion
import com.dp.data.default_data.model.GDefaultQuestions
import com.dp.domain.model.Answer
import com.dp.domain.model.Category
import com.dp.domain.model.GameSession
import com.dp.domain.model.NewGameSession
import com.dp.domain.model.NewUserAnswer
import com.dp.domain.model.Question
import com.dp.domain.model.QuestionType
import com.dp.domain.model.UserAnswer

object DBEntitiesConverter {

    fun DBQuestion.toQuestion(answers: List<DBAnswer>, category: DBCategory): Question {
        return Question(
            id = id,
            type = type,
            category = category.toCategory(),
            question = question,
            difficulty = difficulty,
            createdAt = createdAt,
            updatedAt = updatedAt,
            timesShown = timesShown,
            answers = answers.toAnswers(),
        )
    }

    private fun List<DBAnswer>.toAnswers(): List<Answer> {
        return map { it.toAnswer() }
    }

    private fun DBAnswer.toAnswer(): Answer {
        return Answer(id = id, value = value, isCorrect = isCorrect)
    }

    private fun DBCategory.toCategory(): Category {
        return Category(id = id, name = name)
    }

    fun Question.toDBQuestion(categoryId: Long): DBQuestion {
        return DBQuestion(
            id = id,
            type = type,
            categoryId = categoryId,
            question = question,
            difficulty = difficulty,
            createdAt = createdAt,
            updatedAt = updatedAt,
            timesShown = timesShown,
        )
    }

    fun List<Answer>.toDBAnswers(questionId: Long): List<DBAnswer> {
        return map { it.toDBAnswer(questionId = questionId) }
    }

    private fun Answer.toDBAnswer(questionId: Long): DBAnswer {
        return DBAnswer(id = id, questionId = questionId, value = value, isCorrect = isCorrect)
    }

    fun Category.toDBCategory(): DBCategory {
        return DBCategory(id = id, name = name)
    }

    fun QuestionWithAnswers.toQuestion(): Question {
        return question.toQuestion(answers = answers, category = category)
    }

    fun List<QuestionWithAnswers>.toQuestions(): List<Question> {
        return map { it.toQuestion() }
    }

    private fun List<DBUserAnswer>.toUserAnswers(): List<UserAnswer> {
        return map { it.toUserAnswer() }
    }

    private fun DBUserAnswer.toUserAnswer(): UserAnswer {
        return UserAnswer(
            id = id,
            gameId = gameId,
            questionId = questionId,
            answerId = answerId,
            answeredAt = answeredAt,
            isCorrect = isCorrect,
        )
    }

    fun List<GameWithQuestions>.toGameSessions(): List<GameSession> {
        return map { it.toGameSession() }
    }

    fun GameWithQuestions.toGameSession(): GameSession {
        return GameSession(
            id = gameSession.id,
            timePerGameMs = gameSession.timePerGameMs,
            questionsCount = gameSession.questionsCount,
            mistakesAllowedCount = gameSession.mistakesAllowedCount,
            gameStatus = gameSession.gameStatus,
            createdAt = gameSession.createdAt,
            startedAt = gameSession.startedAt,
            finishedAt = gameSession.finishedAt,
            gameDuration = gameSession.gameDuration,
            questions = questions.toQuestions(),
            userAnswers = userAnswers.toUserAnswers(),
        )
    }

    fun NewGameSession.toDBGameSession(): DBGameSession {
        return DBGameSession(
            id = 0,
            timePerGameMs = timePerGameMs,
            questionsCount = questionsCount,
            mistakesAllowedCount = mistakesAllowedCount,
            gameStatus = gameStatus,
            createdAt = createdAt,
            startedAt = null,
            finishedAt = null,
            gameDuration = null,
        )
    }

    fun NewUserAnswer.toDBUserAnswer(): DBUserAnswer {
        return DBUserAnswer(
            id = 0,
            gameId = gameId,
            questionId = questionId,
            answerId = answerId,
            answeredAt = answeredAt,
            isCorrect = isCorrect,
        )
    }
}