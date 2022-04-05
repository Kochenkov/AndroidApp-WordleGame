package com.vkochenkov.wordlegame.repository

import com.vkochenkov.wordlegame.model.Language
import com.vkochenkov.wordlegame.sharedprefs.LanguageSharedPrefs

class LanguageRepositoryImpl(
    private val sharedPrefs: LanguageSharedPrefs
) : LanguageRepository {

    override fun getLanguage(): Language {
        return sharedPrefs.getLanguage()
    }

    override fun setLanguage(language: Language) {
        sharedPrefs.setLanguage(language)
    }
}