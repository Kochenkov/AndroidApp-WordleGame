package com.vkochenkov.wordlegame.usecase

class DeleteLetterUseCase {

    fun execute(
        currentWord: List<Char>,
        callback: ResultCallback<List<Char>>
    ) {

        if (currentWord.isNotEmpty()) {
            val newWord = currentWord.toMutableList()
            newWord.removeAt(newWord.size - 1)

            callback.onResult(newWord)
        }
    }
}