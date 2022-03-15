package com.vkochenkov.wordlegame.di

import com.vkochenkov.wordlegame.domain.usecase.WordValidationUseCase
import com.vkochenkov.wordlegame.domain.usecase.GetKeyboardRepresentationUseCase
import com.vkochenkov.wordlegame.domain.usecase.GetRandomWordUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetRandomWordUseCase> {
        GetRandomWordUseCase(repository = get())
    }

    factory<WordValidationUseCase> {
        WordValidationUseCase(repository = get())
    }

    factory<GetKeyboardRepresentationUseCase> {
        GetKeyboardRepresentationUseCase(repository = get())
    }
}