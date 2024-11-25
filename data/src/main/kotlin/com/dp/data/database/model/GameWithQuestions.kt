package com.dp.data.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

/**
 * Represents a game session along with its associated questions and user answers.
 *
 * @property gameSession The game session details, including the game's status and timestamps.
 * @property questions The list of questions associated with this game session.
 * @property userAnswers The list of user answers submitted during the game session.
 *
 * The [gameSession] field holds the metadata of the game session, while the [questions] field
 * is populated through a many-to-many relationship between [DBGameSession] and [DBQuestion].
 * The [userAnswers] field links the game session to the user's responses to the questions.
 */
data class GameWithQuestions(
    @Embedded
    val gameSession: DBGameSession,

    @Relation(
        entity = DBQuestion::class,
        parentColumn = "g_id",
        entityColumn = "q_id",
        associateBy = Junction(
            value = DBGameWithQuestionsCrossRef::class,
            parentColumn = "game_id",
            entityColumn = "question_id"
        )
    )
    val questions: List<QuestionWithAnswers>,

    @Relation(
        parentColumn = "g_id",
        entityColumn = "ua_game_id"
    )
    val userAnswers: List<DBUserAnswer>,
)