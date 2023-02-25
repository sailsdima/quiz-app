package com.dp.a360quiz.data.database.default_data

import android.content.Context
import com.dp.a360quiz.di.module.IoDispatcher
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import javax.inject.Inject

private const val VERSION = "version"

class DefaultQuestionsProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : DefaultQuestionsProvider {

    override suspend fun getNewQuestions(lastSavedVersion: Int): DefaultQuestions? =
        withContext(ioDispatcher) {
            val inputStream = context.assets.open("questions.json")
            val jsonString = inputStream.bufferedReader().use(BufferedReader::readText)
            val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)
            val version = jsonObject.getAsJsonPrimitive(VERSION).asInt
            return@withContext if (version > lastSavedVersion)
                gson.fromJson(jsonObject, DefaultQuestions::class.java)
            else null
        }

}