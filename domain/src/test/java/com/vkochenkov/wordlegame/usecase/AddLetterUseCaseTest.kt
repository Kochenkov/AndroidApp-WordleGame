package com.vkochenkov.wordlegame.usecase

import com.vkochenkov.wordlegame.Repository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito

class AddLetterUseCaseTest {

    private val repository = Mockito.mock(Repository::class.java)
    private val useCase = AddLetterUseCase(repository)

    @Test
    fun `invalid length, have no result`() {
        val initialWord = listOf('w', 'o', 'r', 'd')

        Mockito.`when`(repository.getLength()).thenReturn(4)

        useCase.execute(
            char = 'f',
            currentWord = initialWord,
            callback = object :
                ResultCallback<List<Char>> {

                override fun onResult(result: List<Char>) {
                    assertTrue(false)
                }
            }
        )
    }

    @Test
    fun `valid length, have result`() {
        val initialWord = listOf('w', 'o', 'r', 'd')
        val expectedWord = listOf('w', 'o', 'r', 'd', 'f')

        Mockito.`when`(repository.getLength()).thenReturn(5)

        useCase.execute(
            char = 'f',
            currentWord = initialWord,
            callback = object :
                ResultCallback<List<Char>> {

                override fun onResult(result: List<Char>) {
                    assertEquals(expectedWord, result)
                }
            }
        )
    }
}