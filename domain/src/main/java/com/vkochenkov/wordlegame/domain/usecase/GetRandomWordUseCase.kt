package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.Repository
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.domain.model.Language

class GetRandomWordUseCase(
    private val repository: Repository
) {

    fun execute(
        lang: Language,
        length: Int
    ): List<Char> {
        val stringWord = repository.getRandomWord(lang, length)
        return stringWord.toCharArray().toList()
    }
}