package com.vkochenkov.wordlegame.di

import com.vkochenkov.wordlegame.presentation.screen.game.GameViewModel
import com.vkochenkov.wordlegame.presentation.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    
    viewModel<GameViewModel> {
        GameViewModel(
            repository = get(),
            checkWordUseCase = get(),
            getRandomWordUseCase = get()
        )
    }

    viewModel<HomeViewModel> {
        HomeViewModel()
    }
}