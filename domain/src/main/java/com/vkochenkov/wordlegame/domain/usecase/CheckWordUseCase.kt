package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.Repository
import com.vkochenkov.wordlegame.domain.model.Cell

class CheckWordUseCase(
    private val repository: Repository
) {

    fun execute(
        numberOfLetters: Int,
        hiddenWord: List<Char>,
        word: List<Char>,
        callback: CheckWordCallback
    )  {

        if (numberOfLetters!=word.size) {
            callback.onError(ErrorType.NOT_FULL_LINE)
        } else if (!repository.isWordPresent(word.toString())) {
            callback.onError(ErrorType.DOES_NOT_IN_DB)
        } else {
            //todo compare hidden word with word
            callback.onSuccess()
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