package com.vkochenkov.wordlegame.di

import com.vkochenkov.wordlegame.domain.usecase.CheckWordUseCase
import com.vkochenkov.wordlegame.domain.usecase.GetRandomWordUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetRandomWordUseCase> {
        GetRandomWordUseCase(repository = get())
    }

    factory<CheckWordUseCase> {
        CheckWordUseCase(repository = get())
    }
}