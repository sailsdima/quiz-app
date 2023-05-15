package com.dp.a360quiz.data.database.default_data

import com.google.gson.annotations.SerializedName

data class DefaultAnswer(
    @SerializedName("answer") val answer: String,
    @SerializedName("is_correct") val isCorrect: Boolean,
)