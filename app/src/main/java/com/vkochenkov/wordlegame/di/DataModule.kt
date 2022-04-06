package com.vkochenkov.wordlegame.di

import com.vkochenkov.wordlegame.Dao
import com.vkochenkov.wordlegame.LocalStorage
import com.vkochenkov.wordlegame.WordsRepositoryImpl
import com.vkochenkov.wordlegame.WordsRepository
import com.vkochenkov.wordlegame.sharedprefs.LanguageSharedPrefs
import com.vkochenkov.wordlegame.sharedprefs.LengthSharedPrefs
import org.koin.dsl.module

val dataModule = module {

    single {
        Dao()
    }
    single {
        LocalStorage()
    }

    single {
        LanguageSharedPrefs(context = get())
    }

    single {
        LengthSharedPrefs(context = get())
    }

    factory<WordsRepository> {
        WordsRepositoryImpl(
            lengthSharedPrefs = get(),
            languageSharedPrefs = get(),
            storage = get(),
            dao = get()
        )
    }
}