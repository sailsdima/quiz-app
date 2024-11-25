package com.dp.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Represents a question along with its possible answers and associated category.
 *
 * @property question The question details, including its type, difficulty, and content.
 * @property answers The list of answers available for this question.
 * @property category The category that this question belongs to.
 *
 * The [question] field contains the question's metadata, the [answers] field lists all possible
 * answers to the question, and the [category] field provides the category information.
 * The category is linked via the `q_category_id` field, connecting to the [DBCategory] table.
 */
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