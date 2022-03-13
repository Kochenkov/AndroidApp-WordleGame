package com.vkochenkov.wordlegame.presentation.screen.game

import com.vkochenkov.wordlegame.domain.model.Language
import com.vkochenkov.wordlegame.domain.model.Cell

const val DEFAULT_NUMBER_OF_ROWS = 6
const val DEFAULT_NUMBER_OF_LETTERS = 5
val DEFAULT_LANG = Language.RU

data class GameState(
    val numberOfRows: Int = DEFAULT_NUMBER_OF_ROWS,
    val numberOfLetters: Int = DEFAULT_NUMBER_OF_LETTERS,
    val board: Array<Array<Cell>> = Array(DEFAULT_NUMBER_OF_ROWS) {
        Array(DEFAULT_NUMBER_OF_LETTERS) { Cell() }
    },
    val keyboard: List<List<Cell>>,
    val hiddenWord: List<Char>,
    val currentWord: List<Char> = emptyList(),
    val currentRow: Int = 0,
    val gameStatus: Status = Status.PLAYING
) {
    enum class Status {
        PLAYING,
        VICTORY,
        LOSE,
        PAUSE,
        CLOSE
    }
}

