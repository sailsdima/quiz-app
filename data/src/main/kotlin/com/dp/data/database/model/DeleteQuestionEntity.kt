package com.dp.data.database.model

import androidx.room.ColumnInfo

data class DeleteQuestionEntity(
    @ColumnInfo(name = "q_id") val id: Long,
)