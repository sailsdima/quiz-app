package com.dp.data.default_data.model

import com.google.gson.annotations.SerializedName

data class GDefaultAnswer(
    @SerializedName("answer") val answer: String,
    @SerializedName("is_correct") val isCorrect: Boolean,
)