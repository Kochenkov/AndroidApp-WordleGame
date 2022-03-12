package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.Repository
import com.vkochenkov.wordlegame.domain.model.Cell

class GetRandomWordUseCase(
    private val repository: Repository
) {

    fun execute(
        length: Int
    ): List<Char> {
        val stringWord = repository.getRandomWord(length)
        return stringWord.toCharArray().toList()
    }
}