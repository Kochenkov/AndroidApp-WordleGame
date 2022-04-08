package com.vkochenkov.wordlegame.usecase

import com.vkochenkov.wordlegame.Repository
import com.vkochenkov.wordlegame.model.Cell

class AddLetterUseCase(
    private val repository: Repository
) {

    fun execute(
        char: Char,
        board: Array<Array<Cell>>,
        currentWord: List<Char>,
        currentRow: Int,
        callback: ResultCallback<Result>
    ) {
            if (currentWord.size < repository.getLength()) {

                val newWord = currentWord.toMutableList()
                newWord.add(char)

                val newBoard = board.clone()
                for (i in newBoard.indices) {
                    if (i == currentRow) {
                        newBoard[i][newWord.size - 1] = Cell(char)
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