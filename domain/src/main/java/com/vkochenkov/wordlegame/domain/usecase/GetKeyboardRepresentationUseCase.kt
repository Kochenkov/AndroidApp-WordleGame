package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.WordsRepository
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.domain.model.Language

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