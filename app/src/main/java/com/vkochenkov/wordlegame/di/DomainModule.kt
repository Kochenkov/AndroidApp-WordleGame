package com.vkochenkov.wordlegame.di

import com.vkochenkov.wordlegame.usecase.WordValidationUseCase
import com.vkochenkov.wordlegame.usecase.GetKeyboardRepresentationUseCase
import com.vkochenkov.wordlegame.usecase.GetRandomWordUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetRandomWordUseCase(get())
    }

    factory {
        WordValidationUseCase(get())
    }

    factory {
        GetKeyboardRepresentationUseCase(get())
    }
}