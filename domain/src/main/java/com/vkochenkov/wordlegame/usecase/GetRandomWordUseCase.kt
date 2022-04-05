package com.vkochenkov.wordlegame.usecase

import com.vkochenkov.wordlegame.repository.WordsRepository
import com.vkochenkov.wordlegame.model.Language

class GetRandomWordUseCase(
    private val wordsRepository: WordsRepository
) {

    fun execute(
        lang: Language,
        length: Int
    ): List<Char> {
        val stringWord = wordsRepository.getRandomWord(lang, length)
        return stringWord.toList()
    }
}