package com.vkochenkov.wordlegame.domain

interface Repository {


    fun isWordPresent(word: String) : Boolean

    fun getRandomWord(length: Int): String
}