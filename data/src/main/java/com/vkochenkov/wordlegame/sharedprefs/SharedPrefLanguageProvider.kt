package com.vkochenkov.wordlegame.sharedprefs

import android.content.Context
import com.vkochenkov.wordlegame.domain.model.Language

private const val LANG_SHARED_PREFS_STORAGE = "LANG_SHARED_PREFS_STORAGE"
private const val CURRENT_LANG_KEY = "CURRENT_LANG_KEY"
private val defaultLanguage = Language.RU

class SharedPrefLanguageProvider(
    private val context: Context
) {
    private val preferences =
        context.getSharedPreferences(LANG_SHARED_PREFS_STORAGE, Context.MODE_PRIVATE)

    fun getLanguage(): Language {
        val lang: String =
            preferences.getString(CURRENT_LANG_KEY, defaultLanguage.name) ?: defaultLanguage.name
        return Language.valueOf(lang)
    }

    fun setLanguage(language: Language) {
        preferences.edit().putString(CURRENT_LANG_KEY, language.name).apply()
    }
}