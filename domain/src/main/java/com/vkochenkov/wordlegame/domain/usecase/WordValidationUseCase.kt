package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.Repository
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.domain.model.GameStatus
import com.vkochenkov.wordlegame.domain.model.Language

class WordValidationUseCase(
    private val repository: Repository
) {

    fun execute(
        lang: Language,
        numberOfLetters: Int,
        numberOfRows: Int,
        hiddenWord: List<Char>,
        currentWord: List<Char>,
        currentRow: Int,
        callback: UseCaseCallback<ErrorType, Result>
    ) {

        if (numberOfLetters != currentWord.size) {
            callback.onError(ErrorType.NOT_FULL_LINE)
        } else if (!repository.isWordPresent(lang, String(currentWord.toCharArray()))) {
            callback.onError(ErrorType.DOES_NOT_IN_DB)
        } else {
            val outCells = MutableList(numberOfLetters) { Cell() }

            for (i in currentWord.indices) {
                for (j in hiddenWord.indices) {
                    //todo should test
                    if (currentWord[i]==hiddenWord[i]) {
                        outCells[i] = Cell(currentWord[i], Cell.Status.RIGHT)
                        break
                    } else {
                        if (currentWord[i]==hiddenWord[j]) {
                            outCells[i] = Cell(currentWord[i], Cell.Status.PRESENT)
                            break
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
                if (currentRow>=numberOfRows) {
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