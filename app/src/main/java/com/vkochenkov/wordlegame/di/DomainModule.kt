package com.vkochenkov.wordlegame.di

import com.vkochenkov.wordlegame.domain.usecase.WordValidationUseCase
import com.vkochenkov.wordlegame.domain.usecase.GetKeyboardRepresentationUseCase
import com.vkochenkov.wordlegame.domain.usecase.GetRandomWordUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetRandomWordUseCase> {
        GetRandomWordUseCase(wordsRepository = get())
    }

    factory<WordValidationUseCase> {
        WordValidationUseCase(wordsRepository = get())
    }

    factory<GetKeyboardRepresentationUseCase> {
        GetKeyboardRepresentationUseCase(wordsRepository = get())
    }
}