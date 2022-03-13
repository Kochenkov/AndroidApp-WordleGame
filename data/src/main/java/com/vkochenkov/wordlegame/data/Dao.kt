package com.vkochenkov.wordlegame.data

import com.vkochenkov.wordlegame.domain.model.Language

class Dao {

    fun isWordPresent(lang: Language, word: String): Boolean {
        if (lang == Language.RU) {
            for (dictionaryWord in StorageRu.wordsList5) {
                if (word.equals(dictionaryWord)) {
                    return true
                }
            }
        }
        return false
    }

    fun getRandomWord(lang: Language, length: Int): String {
        //todo use $length and $lang after adding DB
        return StorageRu.wordsList5.random()
    }

    fun getKeyboard(lang: Language): List<List<Char>> {
        //todo use $lang after adding DB
        return StorageRu.keyboardRepresentation
    }
}