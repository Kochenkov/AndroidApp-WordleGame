package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.Repository
import com.vkochenkov.wordlegame.domain.model.Language
import org.junit.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito

class GetRandomWordUseCaseTest {

    private val repository = Mockito.mock(Repository::class.java)
    private val useCase = GetRandomWordUseCase(repository)

    @Test
    fun `should return list of chars after get random word`() {
        val length = 5
        val lang = Language.RU

        Mockito.`when`(repository.getRandomWord(lang, length)).thenReturn("test")

        val actual = useCase.execute(lang, length)
        val expected = listOf('t','e','s','t')
        assertEquals(expected, actual)

    }
}