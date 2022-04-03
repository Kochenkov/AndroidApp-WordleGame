package com.vkochenkov.wordlegame.domain

import com.vkochenkov.wordlegame.domain.model.Language

interface LanguageRepository {

    fun getLanguage(): Language

    fun setLanguage(language: Language)
}