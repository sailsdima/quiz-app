package com.dp.domain.usecase.score

interface IncreaseUserScoreUseCase {

    suspend fun increaseUserScoreBy(value: Int)

}