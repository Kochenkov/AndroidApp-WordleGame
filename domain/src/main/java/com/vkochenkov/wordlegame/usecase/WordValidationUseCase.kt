package com.vkochenkov.wordlegame.usecase

import com.vkochenkov.wordlegame.Repository
import com.vkochenkov.wordlegame.model.Cell
import com.vkochenkov.wordlegame.model.GameStatus

class WordValidationUseCase(
    private val repository: Repository
) {

    fun execute(
        hiddenWord: List<Char>,
        currentWord: List<Char>,
        currentRow: Int,
        callback: ExecutionCallback<ErrorType, Result>
    ) {

        val numberOfLetters = repository.getLength()
        val numberOfRows = repository.getRows()

        if (numberOfLetters != currentWord.size) {
            callback.onError(ErrorType.NOT_FULL_LINE)
        } else if (!repository.isWordPresent(String(currentWord.toCharArray()))) {
            callback.onError(ErrorType.DOES_NOT_IN_DB)
        } else {
            val outCells = MutableList(numberOfLetters) { Cell() }

            for (i in currentWord.indices) {
                if (currentWord[i] == hiddenWord[i]) {
                    outCells[i] = Cell(currentWord[i], Cell.Status.RIGHT)
                    continue
                }
                for (j in hiddenWord.indices) {
                    if (currentWord[i] == hiddenWord[j]) {
                        if ((hiddenWord[i] != currentWord[i]) && (outCells[j].letter != hiddenWord[j])) {
                            if (!outCells.contains(Cell(currentWord[i], Cell.Status.PRESENT))) {
                                outCells[i] = Cell(currentWord[i], Cell.Status.PRESENT)
                                break
                            }
                        }
                    }
                }
                if (outCells[i].letter == null) {
                    outCells[i] = Cell(currentWord[i], Cell.Status.WRONG)
                }
            }

            val gameStatus = if (hiddenWord == currentWord) {
                GameStatus.VICTORY
            } else {
                if (currentRow >= numberOfRows - 1) { //currentRow counted from zero
                    GameStatus.LOSE
                } else {
                    GameStatus.PLAYING
                }
            }

            callback.onSuccess(Result(outCells, gameStatus))
        }
    }

    enum class ErrorType {
        NOT_FULL_LINE, DOES_NOT_IN_DB
    }

    data class Result(
        val word: List<Cell>,
        val gameStatus: GameStatus
    )
}