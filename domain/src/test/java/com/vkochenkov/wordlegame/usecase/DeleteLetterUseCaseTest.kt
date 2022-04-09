package com.vkochenkov.wordlegame.usecase

import com.vkochenkov.wordlegame.Repository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito

class DeleteLetterUseCaseTest {

    private val useCase = DeleteLetterUseCase()

    @Test
    fun `empty word, have no result`() {
        val initialWord = listOf<Char>()

        useCase.execute(
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
    fun `delete success, have result`() {
        val initialWord = listOf('w', 'o', 'r', 'd')
        val expectedWord = listOf('w', 'o', 'r')

        useCase.execute(
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