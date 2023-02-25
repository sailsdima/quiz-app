package com.dp.a360quiz.data.database.default_data

import com.google.gson.annotations.SerializedName

data class DefaultQuestions(
    @SerializedName("version") val version: Int,
    @SerializedName("questions") val questions: List<DefaultQuestion>,
)