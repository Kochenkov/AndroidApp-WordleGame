package com.vkochenkov.wordlegame.usecase

import com.vkochenkov.wordlegame.Repository

class GetRandomWordUseCase(
    private val repository: Repository
) {

    fun execute(): List<Char> {
        val stringWord = repository.getRandomWord()
        return stringWord.toList()
    }
}