package com.dp.a360quiz.common.util

import java.text.SimpleDateFormat
import java.util.*

fun convertMsToDateTime(milliseconds: Long): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    dateFormat.timeZone = TimeZone.getDefault()
    return dateFormat.format(Date(milliseconds))
}