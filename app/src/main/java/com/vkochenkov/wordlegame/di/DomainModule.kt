package com.vkochenkov.wordlegame.di

import com.vkochenkov.wordlegame.usecase.WordValidationUseCase
import com.vkochenkov.wordlegame.usecase.GetKeyboardRepresentationUseCase
import com.vkochenkov.wordlegame.usecase.GetRandomWordUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetRandomWordUseCase(wordsRepository = get())
    }

    factory {
        WordValidationUseCase(wordsRepository = get())
    }

    factory {
        GetKeyboardRepresentationUseCase(wordsRepository = get())
    }
}