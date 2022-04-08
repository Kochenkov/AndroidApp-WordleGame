package com.vkochenkov.wordlegame.di

import com.vkochenkov.wordlegame.KeyboardsStorage
import com.vkochenkov.wordlegame.RepositoryImpl
import com.vkochenkov.wordlegame.Repository
import com.vkochenkov.wordlegame.sharedprefs.LanguageSharedPrefs
import com.vkochenkov.wordlegame.sharedprefs.LengthSharedPrefs
import org.koin.dsl.module

val dataModule = module {

    single {
        KeyboardsStorage()
    }

    single {
        LanguageSharedPrefs(get())
    }

    single {
        LengthSharedPrefs(get())
    }

    factory<Repository> {
        RepositoryImpl(get(), get(), get(), get())
    }
}