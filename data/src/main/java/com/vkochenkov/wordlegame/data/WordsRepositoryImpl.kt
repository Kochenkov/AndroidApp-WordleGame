package com.vkochenkov.wordlegame.data

import com.vkochenkov.wordlegame.domain.WordsRepository
import com.vkochenkov.wordlegame.domain.model.Language

class WordsRepositoryImpl(
    private val dao: Dao
): WordsRepository {

    override fun isWordPresent(lang: Language, word: String): Boolean {
        return dao.isWordPresent(lang, word)
    }

    override fun getRandomWord(lang: Language, length: Int): String {
        return dao.getRandomWord(lang, length)
    }

    override fun getKeyboard(lang: Language): List<List<Char>> {
        return dao.getKeyboard(lang)
    }
}