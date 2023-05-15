package com.dp.a360quiz.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class QuestionWithAnswers(
    @Embedded
    val question: DBQuestion,
    @Relation(
        parentColumn = "q_id",
        entityColumn = "a_questionId"
    )
    val answers: List<DBAnswer>,
    @Relation(
        parentColumn = "q_category_id",
        entityColumn = "c_id"
    )
    val category: DBCategory
)