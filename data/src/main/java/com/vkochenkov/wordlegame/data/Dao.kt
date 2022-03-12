package com.vkochenkov.wordlegame.data

class Dao {

    fun isWordPresent(word: String): Boolean {
        for (dictionaryWord in Storage.wordsList5) {
            if (word.equals(dictionaryWord)) {
                return true
            }
        }
        return false
    }
}