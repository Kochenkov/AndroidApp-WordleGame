package com.vkochenkov.wordlegame.usecase

import com.vkochenkov.wordlegame.WordsRepository

class GetRandomWordUseCase(
    private val wordsRepository: WordsRepository
) {

    fun execute(): List<Char> {
        val stringWord = wordsRepository.getRandomWord()
        return stringWord.toList()
    }
}