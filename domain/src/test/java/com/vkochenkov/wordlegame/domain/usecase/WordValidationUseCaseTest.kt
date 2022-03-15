package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.Repository
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.domain.model.GameStatus
import com.vkochenkov.wordlegame.domain.model.Language
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class WordValidationUseCaseTest {

    private val repository = mock(Repository::class.java)
    private val useCase = WordValidationUseCase(repository)
    private val lang = Language.RU

    @Test
    fun `total coincidence`() {
        val hiddenWord = listOf('w', 'o', 'r', 'd')
        val word = listOf('w', 'o', 'r', 'd')
        val expectedResult = WordValidationUseCase.Result(
            listOf(
                Cell('w', Cell.Status.RIGHT),
                Cell('o', Cell.Status.RIGHT),
                Cell('r', Cell.Status.RIGHT),
                Cell('d', Cell.Status.RIGHT)
            ), GameStatus.VICTORY
        )
        `when`(repository.isWordPresent(lang, "word")).thenReturn(true)

        useCase.execute(
            lang = lang,
            numberOfLetters = 4,
            numberOfRows = 2,
            hiddenWord = hiddenWord,
            currentWord = word,
            currentRow = 1,
            callback = object :
                UseCaseCallback<WordValidationUseCase.ErrorType, WordValidationUseCase.Result> {

                override fun onError(error: WordValidationUseCase.ErrorType) {
                    assertTrue(error.toString(), false)
                }

                override fun onSuccess(result: WordValidationUseCase.Result) {
                    assertEquals(expectedResult, result)
                }
            })
    }

    @Test
    fun `partial coincidence`() {
        val hiddenWord = listOf('w', 'o', 'r', 'd')
        val word = listOf('c', 'o', 'a', 'r')
        val expectedResult = WordValidationUseCase.Result(
            listOf(
                Cell('c', Cell.Status.WRONG),
                Cell('o', Cell.Status.RIGHT),
                Cell('a', Cell.Status.WRONG),
                Cell('r', Cell.Status.PRESENT)
            ), GameStatus.PLAYING
        )
        `when`(repository.isWordPresent(lang, "coar")).thenReturn(true)

        useCase.execute(
            lang = lang,
            numberOfLetters = 4,
            numberOfRows = 2,
            hiddenWord = hiddenWord,
            currentWord = word,
            currentRow = 1,
            callback = object : UseCaseCallback<WordValidationUseCase.ErrorType, WordValidationUseCase.Result> {

                override fun onError(error: WordValidationUseCase.ErrorType) {
                    assertTrue(error.toString(), false)
                }

                override fun onSuccess(result: WordValidationUseCase.Result) {
                    assertEquals(expectedResult, result)
                }
            })
    }

    @Test
    fun `lose game`() {
        val hiddenWord = listOf('w', 'o', 'r', 'd')
        val word = listOf('c', 'o', 'a', 'r')
        val expectedResult = WordValidationUseCase.Result(
            listOf(
                Cell('c', Cell.Status.WRONG),
                Cell('o', Cell.Status.RIGHT),
                Cell('a', Cell.Status.WRONG),
                Cell('r', Cell.Status.PRESENT)
            ), GameStatus.LOSE
        )
        `when`(repository.isWordPresent(lang, "coar")).thenReturn(true)

        useCase.execute(
            lang = lang,
            numberOfLetters = 4,
            numberOfRows = 6,
            hiddenWord = hiddenWord,
            currentWord = word,
            currentRow = 6,
            callback = object : UseCaseCallback<WordValidationUseCase.ErrorType, WordValidationUseCase.Result> {

                override fun onError(error: WordValidationUseCase.ErrorType) {
                    assertTrue(error.toString(), false)
                }

                override fun onSuccess(result: WordValidationUseCase.Result) {
                    assertEquals(expectedResult, result)
                }
            })
    }

    @Test
    fun `error length validation`() {
        val hiddenWord = listOf('w', 'o', 'r', 'd')
        val word = listOf('c', 'o', 'a')
        val expectedError = WordValidationUseCase.ErrorType.NOT_FULL_LINE

        `when`(repository.isWordPresent(lang, "coa")).thenReturn(true)

        useCase.execute(
            lang = lang,
            numberOfLetters = 4,
            numberOfRows = 2,
            hiddenWord = hiddenWord,
            currentWord = word,
            currentRow = 1,
            callback = object : UseCaseCallback<WordValidationUseCase.ErrorType, WordValidationUseCase.Result> {

                override fun onError(error: WordValidationUseCase.ErrorType) {
                    assertEquals(expectedError, error)
                }

                override fun onSuccess(result: WordValidationUseCase.Result) {
                    assertTrue(result.toString(), false)
                }
            })
    }

    @Test
    fun `error db validation`() {
        val hiddenWord = listOf('w', 'o', 'r', 'd')
        val word = listOf('g', 'o', 'a', 'l')
        val expectedError = WordValidationUseCase.ErrorType.DOES_NOT_IN_DB

        `when`(repository.isWordPresent(lang, "goal")).thenReturn(false)

        useCase.execute(
            lang = lang,
            numberOfLetters = 4,
            numberOfRows = 1,
            hiddenWord = hiddenWord,
            currentWord = word,
            currentRow = 1,
            callback = object : UseCaseCallback<WordValidationUseCase.ErrorType, WordValidationUseCase.Result> {

                override fun onError(error: WordValidationUseCase.ErrorType) {
                    assertEquals(expectedError, error)
                }

                override fun onSuccess(result: WordValidationUseCase.Result) {
                    assertTrue(result.toString(), false)
                }
            })
    }
}