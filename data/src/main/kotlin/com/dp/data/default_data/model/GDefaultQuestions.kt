package com.dp.data.default_data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the entire default question set, which includes multiple questions
 * and their version information.
 *
 * @property version The version of the default questions set.
 * @property questions A list of [GDefaultQuestion] objects representing individual questions.
 */
data class GDefaultQuestions(
    @SerializedName("version") val version: Int,
    @SerializedName("questions") val questions: List<GDefaultQuestion>,
)