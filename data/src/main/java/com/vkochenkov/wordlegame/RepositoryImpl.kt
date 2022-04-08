package com.vkochenkov.wordlegame

import android.content.Context
import com.vkochenkov.wordlegame.model.Language
import com.vkochenkov.wordlegame.sharedprefs.LanguageSharedPrefs
import com.vkochenkov.wordlegame.sharedprefs.LengthSharedPrefs
import java.io.BufferedReader
import java.io.InputStreamReader

class RepositoryImpl(
    private val lengthSharedPrefs: LengthSharedPrefs,
    private val languageSharedPrefs: LanguageSharedPrefs,
    private val storage: KeyboardsStorage,
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

    override fun getRandomWord(): String {
        return getWordsList().random()
    }

    override fun getKeyboard(): List<List<Char>> {
        return when (languageSharedPrefs.getLanguage()) {
            Language.RU -> storage.getRuKeyboard
            Language.EN -> storage.getEnKeyboard
        }
    }

    override fun getLength(): Int {
        return lengthSharedPrefs.getLength()
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