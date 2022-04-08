package com.vkochenkov.wordlegame.usecase

import com.vkochenkov.wordlegame.Repository
import com.vkochenkov.wordlegame.model.Cell

class GetKeyboardRepresentationUseCase(
    private val repository: Repository
) {

    fun execute(): List<List<Cell>> {
        return repository.getKeyboard().map { list ->
            list.map { char ->
                Cell(char)
            }
        }
    }
}