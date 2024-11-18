package com.dp.data.default_data.model

import com.google.gson.annotations.SerializedName

data class GDefaultQuestions(
    @SerializedName("version") val version: Int,
    @SerializedName("questions") val questions: List<GDefaultQuestion>,
)