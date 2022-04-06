package com.vkochenkov.wordlegame.usecase

import com.vkochenkov.wordlegame.WordsRepository
import com.vkochenkov.wordlegame.model.Cell

class GetKeyboardRepresentationUseCase(
    private val wordsRepository: WordsRepository
) {

    fun execute(): List<List<Cell>> {
        return wordsRepository.getKeyboard().map { list ->
            list.map { char ->
                Cell(char)
            }
        }
    }
}