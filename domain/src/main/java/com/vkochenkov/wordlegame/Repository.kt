package com.vkochenkov.wordlegame

import com.vkochenkov.wordlegame.model.Cell
import com.vkochenkov.wordlegame.model.Language

interface Repository {

    fun isWordPresent(word: String) : Boolean

    fun getRandomWord(): List<Char>

    fun getKeyboard(): List<List<Cell>>

    fun getLength(): Int

    fun getLanguage(): Language

    fun setLanguage(lang: Language)

    fun getRows(): Int
}