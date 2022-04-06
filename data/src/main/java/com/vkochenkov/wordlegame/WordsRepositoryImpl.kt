package com.vkochenkov.wordlegame

import com.vkochenkov.wordlegame.model.Language
import com.vkochenkov.wordlegame.sharedprefs.LanguageSharedPrefs
import com.vkochenkov.wordlegame.sharedprefs.LengthSharedPrefs

class WordsRepositoryImpl(
    private val lengthSharedPrefs: LengthSharedPrefs,
    private val languageSharedPrefs: LanguageSharedPrefs,
    private val storage: LocalStorage,
    private val dao: Dao
) : WordsRepository {

    //var cashedWordsList: List<String>? = null

    override fun isWordPresent(word: String): Boolean {
        //todo create call to dao and db with length and lang
//        for (dictionaryWord in getWordsList()) {
//            if (word.equals(dictionaryWord)) {
//                return true
//            }
//        }
        return false
    }

    override fun getRandomWord(): String {
        //todo create call to dao and db with length and lang
        //return getWordsList().random()
        return ""
    }

    override fun getKeyboard(): List<List<Char>> {
        return when (languageSharedPrefs.getLanguage()) {
            Language.RU -> storage.ruKeyboardRepresentation
            Language.EN -> storage.enKeyboardRepresentation
        }
    }

//    private fun getFileLocation(): String {
//        return when (languageSharedPrefs.getLanguage()) {
//            Language.EN -> TODO()
//            Language.RU -> "ru_words_4_8_letters.txt"
//        }
//    }

//    private fun getWordsList(): List<String> {
//        val reader = BufferedReader(InputStreamReader(context.assets.open(getFileLocation())))
//
//        var str: String? = reader.readLine()
//        val list = ArrayList<String>()
//
//        while (str != null) {
//            if (lengthSharedPrefs.getLength() == str.length) {
//                list.add(str)
//            }
//            str = reader.readLine()
//        }
//        reader.close()
//        return list
//    }
}