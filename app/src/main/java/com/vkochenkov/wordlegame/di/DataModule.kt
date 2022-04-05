package com.vkochenkov.wordlegame.di

import com.vkochenkov.wordlegame.data.Dao
import com.vkochenkov.wordlegame.data.WordsRepositoryImpl
import com.vkochenkov.wordlegame.domain.LanguageRepository
import com.vkochenkov.wordlegame.domain.LengthRepository
import com.vkochenkov.wordlegame.domain.WordsRepository
import com.vkochenkov.wordlegame.sharedprefs.LanguageRepositoryImpl
import com.vkochenkov.wordlegame.sharedprefs.LanguageSharedPrefs
import com.vkochenkov.wordlegame.sharedprefs.LengthRepositoryImpl
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