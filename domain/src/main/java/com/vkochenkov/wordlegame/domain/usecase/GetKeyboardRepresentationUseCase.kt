package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.Repository
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.domain.model.Language

class GetKeyboardRepresentationUseCase(
    private val repository: Repository
) {

    fun execute(lang: Language): List<List<Cell>> {
        val outerList = ArrayList<ArrayList<Cell>>()
        val charsKeyboard = repository.getKeyboard(lang)
        for (line in charsKeyboard) {
            val outerLine = ArrayList<Cell>()
            for (char in line) {
                outerLine.add(
                    Cell(
                        letter = char,
                        status = Cell.Status.DEFAULT
                    )
                )
            }
            outerList.add(outerLine)
        }
        return outerList
    }
}