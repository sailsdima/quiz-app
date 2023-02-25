package com.dp.a360quiz.common.annotation

enum class QuestionType(val type: String) {
    FOUR_ANSWERS("four_answers"),
    YES_NO("yes_no"),
    UNDEFINED("undefined"), ;

    companion object {
        fun getQuestionType(typeString: String): QuestionType {
            return values().find { it.type == typeString } ?: UNDEFINED
        }
    }
}