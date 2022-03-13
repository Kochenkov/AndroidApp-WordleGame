package com.vkochenkov.wordlegame.di

import com.vkochenkov.wordlegame.data.Dao
import com.vkochenkov.wordlegame.data.RepositoryImpl
import com.vkochenkov.wordlegame.domain.Repository
import org.koin.dsl.module

val dataModule = module {

    single<Dao> {
        Dao()
    }

    single<Repository> {
        RepositoryImpl(dao = get())
    }
}