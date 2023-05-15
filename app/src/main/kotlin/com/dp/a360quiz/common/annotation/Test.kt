package com.dp.a360quiz.common.annotation

import kotlinx.coroutines.delay

class TestAnna() {

    suspend fun test(parameter: Int) {
        val rememberedString = parameter.toString()
        val res1 = callingSuspend1()
        println("Res1: $res1")
        val res2 = callingSuspend2()
        println("Res2: $res2")
    }

}


private suspend fun callingSuspend1(): String {
    delay(1000)
    return "callingSuspend"
}

private suspend fun callingSuspend2(): String {
    delay(1000)
    return "callingSuspend2"
}

private fun log(string: String) {

}