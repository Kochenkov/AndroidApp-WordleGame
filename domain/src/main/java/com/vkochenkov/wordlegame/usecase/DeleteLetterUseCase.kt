package com.vkochenkov.wordlegame.usecase

import com.vkochenkov.wordlegame.model.Cell

class DeleteLetterUseCase {

    fun execute(
        board: Array<Array<Cell>>,
        currentWord: List<Char>,
        currentRow: Int,
        callback: ResultCallback<Result>
    ) {

        if (currentWord.isNotEmpty()) {
            val newWord = currentWord.toMutableList()
            newWord.removeAt(newWord.size - 1)
            val newBoard = board.clone()
            for (i in newBoard.indices) {
                if (i == currentRow) {
                    newBoard[i][currentWord.size - 1] = Cell(null)
                    break
                }
            }

            callback.onResult(
                Result(
                    bord = newBoard,
                    word = newWord
                )
            )
        }
    }

    data class Result(
        val bord: Array<Array<Cell>>,
        val word: List<Char>
    )
}