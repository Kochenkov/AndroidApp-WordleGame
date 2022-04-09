package com.vkochenkov.wordlegame.usecase

import com.vkochenkov.wordlegame.Repository
import com.vkochenkov.wordlegame.model.Cell

class AddLetterUseCase(
    private val repository: Repository
) {

    fun execute(
        char: Char,
        currentWord: List<Char>,
        callback: ResultCallback<List<Char>>
    ) {
            if (currentWord.size < repository.getLength()) {

                val newWord = currentWord.toMutableList()
                newWord.add(char)

                callback.onResult(newWord)
            }
    }
}