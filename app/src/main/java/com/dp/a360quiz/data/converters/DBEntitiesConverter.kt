package com.dp.a360quiz.data.converters

import com.dp.a360quiz.common.annotation.QuestionType
import com.dp.a360quiz.data.database.default_data.DefaultQuestion
import com.dp.a360quiz.data.database.default_data.DefaultQuestions
import com.dp.a360quiz.data.database.model.*
import com.dp.a360quiz.domain.model.*
import com.dp.a360quiz.domain.usecase.AnswerUIEntity
import com.dp.a360quiz.domain.usecase.QuestionUIEntity
import com.dp.a360quiz.domain.usecase.UIAnswerType
import com.dp.a360quiz.extensions.logi

object DBEntitiesConverter {

    fun DBQuestion.toQuestion(answers: List<DBAnswer>, category: DBCategory): Question {
        return Question(
            id,
            type,
            category.toCategory(),
            question,
            difficulty,
            createdAt,
            updatedAt,
            timesShown,
            answers.toAnswers(),
        )
    }

    private fun List<DBAnswer>.toAnswers(): List<Answer> {
        return map { it.toAnswer() }
    }

    private fun DBAnswer.toAnswer(): Answer {
        return Answer(id, value, isCorrect)
    }

    private fun DBCategory.toCategory(): Category {
        return Category(id, name)
    }

    fun Question.toDBQuestion(categoryId: Long): DBQuestion {
        return DBQuestion(
            id,
            type,
            categoryId,
            question,
            difficulty,
            createdAt,
            updatedAt,
            timesShown,
        )
    }

    fun List<Answer>.toDBAnswers(questionId: Long): List<DBAnswer> {
        return map { it.toDBAnswer(questionId) }
    }

    private fun Answer.toDBAnswer(questionId: Long): DBAnswer {
        return DBAnswer(id, questionId, value, isCorrect)
    }

    fun Category.toDBCategory(): DBCategory {
        return DBCategory(id, name)
    }

    fun QuestionWithAnswers.toQuestion(): Question {
        return question.toQuestion(answers, category)
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

    fun DefaultQuestions.toQuestions(createdAt: Long): List<Question> {
        return questions.map { it.toQuestion(createdAt) }
    }

    fun DefaultQuestion.toQuestion(createdAt: Long): Question {
        logi("$question $category")
        return Question(
            id = id,
            type = QuestionType.getQuestionType(type),
            category = Category(0, category),
            question = question,
            difficulty = difficulty,
            createdAt = createdAt,
            updatedAt = createdAt,
            timesShown = 0,
            answers = answers.map { Answer(0, it.answer, it.isCorrect) },
        )
    }

    fun Question.toQuestionUIEntity(userAnswerId: Long? = null, correctAnswerId: Long? = null): QuestionUIEntity {
        return QuestionUIEntity(
            id = id,
            type = type,
            question = question,
            answers = answers.toAnswerUIEntities(userAnswerId, correctAnswerId),
        )
    }

    private fun List<Answer>.toAnswerUIEntities(userAnswerId: Long?, correctAnswerId: Long?): List<AnswerUIEntity> {
        return map { it.toAnswerUIEntity(userAnswerId, correctAnswerId) }
    }

    private fun Answer.toAnswerUIEntity(userAnswerId: Long?, correctAnswerId: Long?): AnswerUIEntity {
        return AnswerUIEntity(
            id = id,
            answer = value,
            answerType = when {
                id == userAnswerId && userAnswerId == correctAnswerId -> UIAnswerType.USER_CORRECT_ANSWER
                id == userAnswerId && userAnswerId != correctAnswerId -> UIAnswerType.ANSWERED_WRONG
                id == correctAnswerId -> UIAnswerType.CORRECT_ANSWER
                else -> UIAnswerType.NOT_ANSWERED
            }
        )
    }
}