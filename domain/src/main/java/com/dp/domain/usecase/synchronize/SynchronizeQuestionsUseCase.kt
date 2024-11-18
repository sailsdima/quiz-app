package com.dp.domain.usecase.synchronize

fun interface SynchronizeQuestionsUseCase {
    suspend operator fun invoke()
}