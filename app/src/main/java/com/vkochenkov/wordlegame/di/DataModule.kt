package com.vkochenkov.wordlegame.di

import com.vkochenkov.wordlegame.data.Dao
import com.vkochenkov.wordlegame.data.WordsRepositoryImpl
import com.vkochenkov.wordlegame.domain.LanguageRepository
import com.vkochenkov.wordlegame.domain.WordsRepository
import com.vkochenkov.wordlegame.sharedprefs.LanguageRepositoryImpl
import com.vkochenkov.wordlegame.sharedprefs.SharedPrefLanguageProvider
import org.koin.dsl.module

val dataModule = module {

    single {
        Dao()
    }

    single<WordsRepository> {
        WordsRepositoryImpl(dao = get())
    }

    single {
        SharedPrefLanguageProvider(context = get())
    }

    single<LanguageRepository> {
        LanguageRepositoryImpl(sharedPrefs = get())
    }
}