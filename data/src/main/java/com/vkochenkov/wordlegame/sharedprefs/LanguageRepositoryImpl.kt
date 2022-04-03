package com.vkochenkov.wordlegame.sharedprefs

import com.vkochenkov.wordlegame.domain.LanguageRepository
import com.vkochenkov.wordlegame.domain.model.Language

class LanguageRepositoryImpl(
    private val sharedPrefs: SharedPrefLanguageProvider
): LanguageRepository {

    override fun getLanguage(): Language {
        return sharedPrefs.getLanguage()
    }

    override fun setLanguage(language: Language) {
        sharedPrefs.setLanguage(language)
    }
}