package com.vkochenkov.wordlegame.presentation.screen.game

import com.vkochenkov.wordlegame.presentation.model.Cell

const val DEFAULT_NUMBER_OF_ROWS = 6
const val DEFAULT_NUMBER_OF_LETTERS_PER_WORD = 5

data class GameState(
    val field: List<List<Cell>> = List(DEFAULT_NUMBER_OF_ROWS) {
        List(DEFAULT_NUMBER_OF_LETTERS_PER_WORD) { Cell() }
    },
    val hiddenWord: List<Char>
)
