package com.vkochenkov.wordlegame.di

import com.vkochenkov.wordlegame.usecase.AddLetterUseCase
import com.vkochenkov.wordlegame.usecase.DeleteLetterUseCase
import com.vkochenkov.wordlegame.usecase.WordValidationUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        WordValidationUseCase(get())
    }

    factory {
        AddLetterUseCase(get())
    }

    factory {
        DeleteLetterUseCase()
    }
}