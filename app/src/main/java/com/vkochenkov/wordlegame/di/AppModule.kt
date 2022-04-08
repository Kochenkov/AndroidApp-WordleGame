package com.vkochenkov.wordlegame.di

import com.vkochenkov.wordlegame.presentation.screen.game.GameViewModel
import com.vkochenkov.wordlegame.presentation.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    
    viewModel {
        GameViewModel(get(), get(), get(), get())
    }

    viewModel {
        HomeViewModel()
    }
}