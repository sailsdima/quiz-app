package com.dp.data.default_data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing a question in the default questions file.
 *
 * @property id The unique identifier for the question.
 * @property type The type of the question (e.g., multiple choice, true/false).
 * @property category The category to which the question belongs.
 * @property question The text of the question.
 * @property difficulty The difficulty level of the question, represented as an integer.
 * @property answers A list of answers to the question.
 */
data class GDefaultQuestion(
    @SerializedName("id") val id: Long,
    @SerializedName("type") val type: String,
    @SerializedName("category") val category: String,
    @SerializedName("question") val question: String,
    @SerializedName("difficulty") val difficulty: Int,
    @SerializedName("answers") val answers: List<GDefaultAnswer>,
)