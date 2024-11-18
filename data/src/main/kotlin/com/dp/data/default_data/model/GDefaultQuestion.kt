package com.dp.data.default_data.model

import com.google.gson.annotations.SerializedName

data class GDefaultQuestion(
    @SerializedName("id") val id: Long,
    @SerializedName("type") val type: String,
    @SerializedName("category") val category: String,
    @SerializedName("question") val question: String,
    @SerializedName("difficulty") val difficulty: Int,
    @SerializedName("answers") val answers: List<GDefaultAnswer>,
)