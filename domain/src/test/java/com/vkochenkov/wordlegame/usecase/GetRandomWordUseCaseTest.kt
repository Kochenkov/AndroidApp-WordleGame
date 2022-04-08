package com.vkochenkov.wordlegame.usecase

import com.vkochenkov.wordlegame.Repository
import org.junit.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito

class GetRandomWordUseCaseTest {

    private val repository = Mockito.mock(Repository::class.java)
    private val useCase = GetRandomWordUseCase(repository)

    @Test
    fun `should return list of chars after get random word`() {
        Mockito.`when`(repository.getRandomWord()).thenReturn("test")

        val actual = useCase.execute()
        val expected = listOf('t','e','s','t')
        assertEquals(expected, actual)

    }
}