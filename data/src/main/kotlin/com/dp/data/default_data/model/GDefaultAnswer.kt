package com.dp.data.default_data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing an answer to a question in the default questions file.
 *
 * @property answer The text of the answer.
 * @property isCorrect A boolean indicating if the answer is correct.
 */
data class GDefaultAnswer(
    @SerializedName("answer") val answer: String,
    @SerializedName("is_correct") val isCorrect: Boolean,
)