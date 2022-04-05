package com.vkochenkov.wordlegame.di

import com.vkochenkov.wordlegame.local.Dao
import com.vkochenkov.wordlegame.repository.WordsRepositoryImpl
import com.vkochenkov.wordlegame.repository.LanguageRepository
import com.vkochenkov.wordlegame.repository.LengthRepository
import com.vkochenkov.wordlegame.repository.WordsRepository
import com.vkochenkov.wordlegame.repository.LanguageRepositoryImpl
import com.vkochenkov.wordlegame.sharedprefs.LanguageSharedPrefs
import com.vkochenkov.wordlegame.repository.LengthRepositoryImpl
import com.vkochenkov.wordlegame.sharedprefs.LengthSharedPrefs
import org.koin.dsl.module

val dataModule = module {

    single {
        Dao()
    }

    single<WordsRepository> {
        WordsRepositoryImpl(dao = get())
    }

    single {
        LanguageSharedPrefs(context = get())
    }

    single {
        LengthSharedPrefs(context = get())
    }

    single<LanguageRepository> {
        LanguageRepositoryImpl(sharedPrefs = get())
    }

    single<LengthRepository> {
        LengthRepositoryImpl(sharedPrefs = get())
    }
}