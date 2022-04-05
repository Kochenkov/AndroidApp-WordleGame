package com.vkochenkov.wordlegame.usecase

import com.vkochenkov.wordlegame.repository.WordsRepository
import com.vkochenkov.wordlegame.model.Cell
import com.vkochenkov.wordlegame.model.Language

class GetKeyboardRepresentationUseCase(
    private val wordsRepository: WordsRepository
) {

    fun execute(lang: Language): List<List<Cell>> {
        return wordsRepository.getKeyboard(lang).map { list ->
            list.map { char ->
                Cell(char)
            }
        }
    }
}