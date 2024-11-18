package com.dp.domain.model

enum class QuestionType(val type: String) {
    FOUR_ANSWERS("four_answers"),
    YES_NO("yes_no"),
    UNDEFINED("undefined"), ;

    companion object {
        fun getQuestionType(typeString: String): QuestionType {
            return entries.find { it.type == typeString } ?: UNDEFINED
        }
    }
}