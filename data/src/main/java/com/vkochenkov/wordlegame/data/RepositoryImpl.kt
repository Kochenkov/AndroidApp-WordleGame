package com.vkochenkov.wordlegame.data

import com.vkochenkov.wordlegame.domain.Repository

class RepositoryImpl(
    private val dao: Dao
): Repository {

    override fun isWordPresent(word: String): Boolean {
        return dao.isWordPresent(word)
    }

    override fun getRandomWord(length: Int): String {
        return dao.getRandomWord(length)
    }
}