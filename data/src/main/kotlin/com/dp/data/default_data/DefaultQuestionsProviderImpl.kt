package com.dp.data.default_data

import android.content.Context
import com.dp.core.di.IoDispatcher
import com.dp.data.default_data.converter.toDefaultQuestions
import com.dp.data.default_data.model.GDefaultQuestions
import com.dp.domain.model.default_data.DefaultQuestions
import com.dp.domain.repository.DefaultQuestionsProvider
import com.dp.domain.time.CurrentTimeProvider
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
    private val currentTimeProvider: CurrentTimeProvider,
) : DefaultQuestionsProvider {

    override suspend fun getNewQuestions(lastSavedVersion: Int): DefaultQuestions? =
        withContext(ioDispatcher) {
            val inputStream = context.assets.open("questions.json")
            val jsonString = inputStream.bufferedReader().use(BufferedReader::readText)
            val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)
            val version = jsonObject.getAsJsonPrimitive(VERSION).asInt
            return@withContext if (version > lastSavedVersion)
                gson.fromJson(jsonObject, GDefaultQuestions::class.java)
                    .toDefaultQuestions(currentTimeProvider.currentTime)
            else null
        }

}