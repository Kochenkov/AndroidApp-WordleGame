package com.vkochenkov.wordlegame.repository

import com.vkochenkov.wordlegame.model.Language

interface LanguageRepository {

    fun getLanguage(): Language

    fun setLanguage(language: Language)
}