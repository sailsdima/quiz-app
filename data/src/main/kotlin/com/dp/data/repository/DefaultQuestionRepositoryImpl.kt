package com.dp.data.repository

import android.content.Context
import com.dp.core.di.IoDispatcher
import com.dp.data.default_data.converter.toDefaultQuestions
import com.dp.data.default_data.model.GDefaultQuestions
import com.dp.domain.model.default_data.DefaultQuestions
import com.dp.domain.repository.DefaultQuestionRepository
import com.dp.domain.time.CurrentTimeProvider
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import javax.inject.Inject

/**
 * Implementation of [DefaultQuestionRepository] to fetch default questions from assets.
 */
class DefaultQuestionRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val currentTimeProvider: CurrentTimeProvider,
) : DefaultQuestionRepository {

    /**
     * Fetches new questions from the assets folder.
     * Compares the saved version with the current version to decide if new questions should be fetched.
     *
     * @param lastSavedVersion The version of questions previously saved.
     * @return DefaultQuestions object if new questions are available, null otherwise.
     */
    override suspend fun fetchUpdatedQuestionsIfAvailable(lastSavedVersion: Int): DefaultQuestions? =
        withContext(ioDispatcher) {
            val inputStream = context.assets.open("questions.json")
            val jsonString = inputStream.bufferedReader().use(BufferedReader::readText)
            val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)
            val version = jsonObject.getAsJsonPrimitive("version").asInt
            return@withContext if (version > lastSavedVersion) {
                gson.fromJson(jsonObject, GDefaultQuestions::class.java)
                    .toDefaultQuestions(createdAt = currentTimeProvider.currentTime)
            } else null
        }
}