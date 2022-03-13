package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.Repository
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.domain.model.Language

class CheckWordUseCase(
    private val repository: Repository
) {

    fun execute(
        lang: Language,
        numberOfLetters: Int,
        hiddenWord: List<Char>,
        word: List<Char>,
        callback: Callback
    ) {

        if (numberOfLetters != word.size) {
            callback.onError(ErrorType.NOT_FULL_LINE)
        } else if (!repository.isWordPresent(lang, word.toString())) {
            callback.onError(ErrorType.DOES_NOT_IN_DB)
        } else {
            //todo compare hidden word with word
            for (i in 0..numberOfLetters) {

            }
            //callback.onSuccess()
        }
    }

    interface Callback {
        fun onError(error: ErrorType)
        fun onSuccess(list: List<Cell>)
    }

    enum class ErrorType {
        NOT_FULL_LINE, DOES_NOT_IN_DB
    }
}