package com.dp.a360quiz.data.database.default_data

import com.google.gson.annotations.SerializedName

data class DefaultQuestion(
    @SerializedName("id") val id: Long,
    @SerializedName("type") val type: String,
    @SerializedName("category") val category: String,
    @SerializedName("question") val question: String,
    @SerializedName("difficulty") val difficulty: Int,
    @SerializedName("answers") val answers: List<DefaultAnswer>,
)