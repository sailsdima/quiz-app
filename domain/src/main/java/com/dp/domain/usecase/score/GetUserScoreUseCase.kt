package com.dp.domain.usecase.score

import kotlinx.coroutines.flow.Flow

interface GetUserScoreUseCase {

    val userScore: Flow<Int>

}