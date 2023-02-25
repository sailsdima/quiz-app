package com.dp.a360quiz.extensions

import timber.log.Timber

inline fun <reified T> T.logi(message: String) = Timber.i(message)

inline fun <reified T> T.logd(message: String) = Timber.d(message)

inline fun <reified T> T.logw(message: String) = Timber.w(message)

inline fun <reified T> T.logw(error: Throwable) = Timber.w(error)

inline fun <reified T> T.loge(message: String) = Timber.e(message)

inline fun <reified T> T.loge(error: Throwable) = Timber.e(error)

inline fun <reified T> T.loge(error: Throwable, message: String) = Timber.e(error, message)

inline fun <reified T> T.wtf(error: Throwable) = Timber.wtf(error)

inline fun <reified T> T.wtf(message: String) = Timber.wtf(message)