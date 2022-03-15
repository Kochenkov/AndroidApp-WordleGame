package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.Repository
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.domain.model.Language
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CheckWordUseCaseTest {

    private val repository = mock(Repository::class.java)
    private val useCase = CheckWordUseCase(repository)
    private val lang = Language.RU

    @Test
    fun `total coincidence`() {
        val length = 4
        val hiddenWord = listOf('w', 'o', 'r', 'd')
        val word = listOf('w', 'o', 'r', 'd')
        val expectedAnswer = listOf(
            Cell('w', Cell.Status.RIGHT),
            Cell('o', Cell.Status.RIGHT),
            Cell('r', Cell.Status.RIGHT),
            Cell('d', Cell.Status.RIGHT)
        )
        `when`(repository.isWordPresent(lang, "word")).thenReturn(true)

        useCase.execute(
            lang = lang,
            numberOfLetters = length,
            numberOfRows = 1,
            hiddenWord = hiddenWord,
            word = word,
            callback = object : UseCaseCallback<CheckWordUseCase.ErrorType, List<Cell>> {

                override fun onError(error: CheckWordUseCase.ErrorType) {
                    assertTrue(error.toString(), false)
                }

                override fun onSuccess(result: List<Cell>) {
                    assertEquals(expectedAnswer, result)
                }
            })
    }

    @Test
    fun `partial coincidence`() {
        val length = 4
        val hiddenWord = listOf('w', 'o', 'r', 'd')
        val word = listOf('c', 'o', 'a', 'r')
        val expectedAnswer = listOf(
            Cell('c', Cell.Status.WRONG),
            Cell('o', Cell.Status.RIGHT),
            Cell('a', Cell.Status.WRONG),
            Cell('r', Cell.Status.PRESENT)
        )
        `when`(repository.isWordPresent(lang, "coar")).thenReturn(true)

        useCase.execute(
            lang = lang,
            numberOfLetters = length,
            numberOfRows = 1,
            hiddenWord = hiddenWord,
            word = word,
            callback = object : UseCaseCallback<CheckWordUseCase.ErrorType, List<Cell>> {

                override fun onError(error: CheckWordUseCase.ErrorType) {
                    assertTrue(error.toString(), false)
                }

                override fun onSuccess(result: List<Cell>) {
                    assertEquals(expectedAnswer, result)
                }
            })
    }

    @Test
    fun `error length validation`() {
        val length = 4
        val hiddenWord = listOf('w', 'o', 'r', 'd')
        val word = listOf('c', 'o', 'a')
        val expectedError = CheckWordUseCase.ErrorType.NOT_FULL_LINE

        `when`(repository.isWordPresent(lang, "coa")).thenReturn(true)

        useCase.execute(
            lang = lang,
            numberOfLetters = length,
            numberOfRows = 1,
            hiddenWord = hiddenWord,
            word = word,
            callback = object : UseCaseCallback<CheckWordUseCase.ErrorType, List<Cell>> {

                override fun onError(error: CheckWordUseCase.ErrorType) {
                    assertEquals(expectedError, error)
                }

                override fun onSuccess(result: List<Cell>) {
                    assertTrue(result.toString(), false)
                }
            })
    }

    @Test
    fun `error db validation`() {
        val length = 4
        val hiddenWord = listOf('w', 'o', 'r', 'd')
        val word = listOf('g', 'o', 'a', 'l')
        val expectedError = CheckWordUseCase.ErrorType.DOES_NOT_IN_DB

        `when`(repository.isWordPresent(lang, "goal")).thenReturn(false)

        useCase.execute(
            lang = lang,
            numberOfLetters = length,
            numberOfRows = 1,
            hiddenWord = hiddenWord,
            word = word,
            callback = object : UseCaseCallback<CheckWordUseCase.ErrorType, List<Cell>> {

                override fun onError(error: CheckWordUseCase.ErrorType) {
                    assertEquals(expectedError, error)
                }

                override fun onSuccess(result: List<Cell>) {
                    assertTrue(result.toString(), false)
                }
            })
    }
}