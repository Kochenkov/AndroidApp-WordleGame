package com.vkochenkov.wordlegame.presentation.screen.game

import com.vkochenkov.wordlegame.domain.model.Cell

const val DEFAULT_NUMBER_OF_ROWS = 6
const val DEFAULT_NUMBER_OF_LETTERS = 5

data class GameState(
    val numberOfRows: Int = DEFAULT_NUMBER_OF_ROWS,
    val numberOfLetters: Int = DEFAULT_NUMBER_OF_LETTERS,
    val field: List<List<Cell>> = List(DEFAULT_NUMBER_OF_ROWS) {
        List(DEFAULT_NUMBER_OF_LETTERS) { Cell() }
    },
    val hiddenWord: List<Char>,
    val currentTippedWord: List<Char> = emptyList(),
    val currentRow: Int = 1,
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

