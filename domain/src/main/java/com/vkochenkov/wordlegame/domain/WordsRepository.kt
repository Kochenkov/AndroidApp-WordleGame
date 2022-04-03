package com.vkochenkov.wordlegame.domain

import com.vkochenkov.wordlegame.domain.model.Language

interface WordsRepository {

    fun isWordPresent(lang: Language, word: String) : Boolean

    fun getRandomWord(lang: Language, length: Int): String

    fun getKeyboard(lang: Language): List<List<Char>>
}