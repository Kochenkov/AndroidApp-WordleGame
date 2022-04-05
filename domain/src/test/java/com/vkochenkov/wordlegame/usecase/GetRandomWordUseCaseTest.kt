package com.vkochenkov.wordlegame.usecase

import com.vkochenkov.wordlegame.repository.WordsRepository
import com.vkochenkov.wordlegame.model.Language
import org.junit.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito

class GetRandomWordUseCaseTest {

    private val repository = Mockito.mock(WordsRepository::class.java)
    private val useCase = GetRandomWordUseCase(repository)
    private val lang = Language.RU


    @Test
    fun `should return list of chars after get random word`() {
        val length = 5

        Mockito.`when`(repository.getRandomWord(lang, length)).thenReturn("test")

        val actual = useCase.execute(lang, length)
        val expected = listOf('t','e','s','t')
        assertEquals(expected, actual)

    }
}