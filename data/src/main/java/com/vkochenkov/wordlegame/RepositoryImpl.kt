package com.vkochenkov.wordlegame

import android.content.Context
import com.vkochenkov.wordlegame.model.Cell
import com.vkochenkov.wordlegame.model.Language
import com.vkochenkov.wordlegame.sharedprefs.LanguageSharedPrefs
import com.vkochenkov.wordlegame.sharedprefs.LengthSharedPrefs
import java.io.BufferedReader
import java.io.InputStreamReader

private const val DEFAULT_NUMBER_OF_ROWS = 6

class RepositoryImpl(
    private val lengthSharedPrefs: LengthSharedPrefs,
    private val languageSharedPrefs: LanguageSharedPrefs,
    private val keyboardStorage: KeyboardsStorage,
    private val context: Context
) : Repository {

    override fun isWordPresent(word: String): Boolean {
        //todo улучшить алгоритм до бинарного поиска
        for (dictionaryWord in getWordsList()) {
            if (word == dictionaryWord) {
                return true
            }
        }
        return false
    }

    override fun getRandomWord(): List<Char> {
        return getWordsList().random().toList()
    }

    override fun getKeyboard(): List<List<Cell>> {
        val keyboard = when (languageSharedPrefs.getLanguage()) {
            Language.RU -> keyboardStorage.getRuKeyboard
            Language.EN -> keyboardStorage.getEnKeyboard
        }
        return keyboard.map { list ->
            list.map { char ->
                Cell(char)
            }
        }
    }

    override fun getLength(): Int {
        return lengthSharedPrefs.getLength()
    }

    override fun getRows(): Int {
        return DEFAULT_NUMBER_OF_ROWS
    }

    private fun getFileLocation(): String {
        return when (languageSharedPrefs.getLanguage()) {
            Language.EN -> "en_words_4_8_letters.txt"
            Language.RU -> "ru_words_4_8_letters.txt"
        }
    }

    private fun getWordsList(): List<String> {
        val reader = BufferedReader(InputStreamReader(context.assets.open(getFileLocation())))

        var str: String? = reader.readLine()
        val list = ArrayList<String>()

        while (str != null) {
            if (lengthSharedPrefs.getLength() == str.length) {
                list.add(str)
            }
            str = reader.readLine()
        }
        reader.close()
        return list
    }
}