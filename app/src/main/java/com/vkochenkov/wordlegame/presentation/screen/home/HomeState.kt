package com.vkochenkov.wordlegame.presentation.screen.home

import com.vkochenkov.wordlegame.domain.model.GameStatus

data class HomeState(
    val lastGameStatus: GameStatus
)
