package com.vkochenkov.wordlegame

interface Repository {

    fun isWordPresent(word: String) : Boolean

    fun getRandomWord(): String

    fun getKeyboard(): List<List<Char>>

    fun getLength(): Int
}