package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.WordsRepository
import com.vkochenkov.wordlegame.domain.model.Language

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