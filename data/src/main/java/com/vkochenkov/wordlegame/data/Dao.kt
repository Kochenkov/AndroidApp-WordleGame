package com.vkochenkov.wordlegame.data

import com.vkochenkov.wordlegame.domain.model.Language
import java.io.BufferedReader
import java.io.FileReader

class Dao {

    //todo

    fun isWordPresent(lang: Language, word: String): Boolean {
        if (lang == Language.RU) {
            for (dictionaryWord in LocalStorage.wordsList) {
                if (word.equals(dictionaryWord)) {
                    return true
                }
            }
        }
        return false
    }

    fun getRandomWord(lang: Language, length: Int): String {
        //todo use $length and $lang after adding DB
        return LocalStorage.wordsList.random()
    }

    fun getKeyboard(lang: Language): List<List<Char>> {
        return when (lang) {
            Language.RU -> LocalStorage.ruKeyboardRepresentation
            Language.EN -> LocalStorage.enKeyboardRepresentation
        }
    }

    //todo?
    fun initCashedWordsList(wordLength: Int) {
        val reader  = BufferedReader(FileReader("src/main/resources/ru_words_4_8_letters.txt"))

        var str: String? = reader.readLine()
        val list = ArrayList<String>()

        while (str != null) {
            if (wordLength == str.length) {
                list.add(str)
            }
            str = reader.readLine()
        }
        reader.close()
    }
}