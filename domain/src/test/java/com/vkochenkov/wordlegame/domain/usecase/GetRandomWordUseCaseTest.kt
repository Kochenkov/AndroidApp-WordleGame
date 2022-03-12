package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.Repository
import org.junit.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito

class GetRandomWordUseCaseTest {

    private val repository = Mockito.mock(Repository::class.java)
    private val useCase = GetRandomWordUseCase(repository)

    @Test
    fun `should return list of chars after get random word`() {
        val length = 5
        Mockito.`when`(repository.getRandomWord(length)).thenReturn("test")

        val actual = useCase.execute(length)
        val expected = listOf('t','e','s','t')
        assertEquals(expected, actual)

    }
}