package com.dp.a360quiz.domain.usecase.score

interface IncreaseUserScoreUseCase {

    suspend fun increaseUserScoreBy(value: Int)

}