package com.dp.a360quiz.data.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

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