package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.Repository
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.domain.model.Language

class GetKeyboardRepresentationUseCase(
    private val repository: Repository
) {

    fun execute(lang: Language): List<List<Cell>> {
        return repository.getKeyboard(lang).map { list ->
            list.map { char ->
                Cell(char)
            }
        }
    }
}