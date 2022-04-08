package com.vkochenkov.wordlegame

import com.vkochenkov.wordlegame.model.Cell

interface Repository {

    fun isWordPresent(word: String) : Boolean

    fun getRandomWord(): List<Char>

    fun getKeyboard(): List<List<Cell>>

    fun getLength(): Int

    fun getRows(): Int
}