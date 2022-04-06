package com.vkochenkov.wordlegame

interface WordsRepository {

    fun isWordPresent(word: String) : Boolean

    fun getRandomWord(): String

    fun getKeyboard(): List<List<Char>>
}