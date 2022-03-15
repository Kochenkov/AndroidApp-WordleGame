package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.Repository
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.domain.model.Language

class CheckWordUseCase(
    private val repository: Repository
) {

    fun execute(
        lang: Language,
        numberOfLetters: Int,
        numberOfRows: Int,
        hiddenWord: List<Char>,
        word: List<Char>,
        callback: UseCaseCallback<ErrorType, List<Cell>>
    ) {

        if (numberOfLetters != word.size) {
            callback.onError(ErrorType.NOT_FULL_LINE)
        } else if (!repository.isWordPresent(lang, String(word.toCharArray()))) {
            callback.onError(ErrorType.DOES_NOT_IN_DB)
        } else {
            val outCells = MutableList(numberOfLetters) { Cell() }
            for (i in word.indices) {
                for (j in hiddenWord.indices) {
                    //todo should test
                    if (word[i]==hiddenWord[i]) {
                        outCells[i] = Cell(word[i], Cell.Status.RIGHT)
                        break
                    } else {
                        if (word[i]==hiddenWord[j]) {
                            outCells[i] = Cell(word[i], Cell.Status.PRESENT)
                            break
                        }
                    }
                }
                if (outCells[i].letter == null) {
                    outCells[i] = Cell(word[i], Cell.Status.WRONG)
                }
            }
            callback.onSuccess(outCells)
        }
    }

    enum class ErrorType {
        NOT_FULL_LINE, DOES_NOT_IN_DB
    }
}