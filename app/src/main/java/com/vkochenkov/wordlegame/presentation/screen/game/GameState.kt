package com.vkochenkov.wordlegame.presentation.screen.game

import com.vkochenkov.wordlegame.model.Cell
import com.vkochenkov.wordlegame.model.GameStatus

data class GameState(
    val board: Array<Array<Cell>>,
    val keyboard: List<List<Cell>>,
    val hiddenWord: List<Char>,
    val currentWord: List<Char> = emptyList(),
    val currentRow: Int = 0,
    val gameStatus: GameStatus = GameStatus.PLAYING
)