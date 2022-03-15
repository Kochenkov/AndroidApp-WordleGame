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
    fun `total coincidence - victory`() {
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
    fun `partial coincidence test 1`() {
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
            numberOfRows = 3,
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
    fun `partial coincidence test 2`() {
        val hiddenWord = listOf('a', 'c', 'o', 'a')
        val word = listOf('a', 'n', 'a', 'c')
        val expectedResult = WordValidationUseCase.Result(
            listOf(
                Cell('a', Cell.Status.RIGHT),
                Cell('n', Cell.Status.WRONG),
                Cell('a', Cell.Status.PRESENT),
                Cell('c', Cell.Status.PRESENT)
            ), GameStatus.PLAYING
        )
        `when`(repository.isWordPresent(lang, "anac")).thenReturn(true)

        useCase.execute(
            lang = lang,
            numberOfLetters = 4,
            numberOfRows = 3,
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
    fun `partial coincidence test 3`() {
        val hiddenWord = listOf('a', 'c', 'o', 'f')
        val word = listOf('a', 'n', 'a', 'c')
        val expectedResult = WordValidationUseCase.Result(
            listOf(
                Cell('a', Cell.Status.RIGHT),
                Cell('n', Cell.Status.WRONG),
                Cell('a', Cell.Status.WRONG),
                Cell('c', Cell.Status.PRESENT)
            ), GameStatus.PLAYING
        )
        `when`(repository.isWordPresent(lang, "anac")).thenReturn(true)

        useCase.execute(
            lang = lang,
            numberOfLetters = 4,
            numberOfRows = 3,
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
    fun `partial coincidence test 4`() {
        val hiddenWord = listOf('л', 'а', 'р', 'е', 'к')
        val word = listOf('а', 'г', 'р', 'а', 'р')
        val expectedResult = WordValidationUseCase.Result(
            listOf(
                Cell('а', Cell.Status.PRESENT),
                Cell('г', Cell.Status.WRONG),
                Cell('р', Cell.Status.RIGHT),
                Cell('а', Cell.Status.WRONG),
                Cell('р', Cell.Status.WRONG)
            ), GameStatus.PLAYING
        )
        `when`(repository.isWordPresent(lang, "аграр")).thenReturn(true)

        useCase.execute(
            lang = lang,
            numberOfLetters = 5,
            numberOfRows = 3,
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
            currentRow = 5,
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