package com.vkochenkov.wordlegame.repository

import com.vkochenkov.wordlegame.model.Language

interface WordsRepository {

    fun isWordPresent(lang: Language, word: String) : Boolean

    fun getRandomWord(lang: Language, length: Int): String

    fun getKeyboard(lang: Language): List<List<Char>>
}