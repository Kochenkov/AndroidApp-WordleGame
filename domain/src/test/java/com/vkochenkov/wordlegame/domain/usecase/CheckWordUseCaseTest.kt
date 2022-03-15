package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.Repository
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.domain.model.Language
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.*

class CheckWordUseCaseTest {

    private val repository = mock(Repository::class.java)
    private val useCase = CheckWordUseCase(repository)
    private val lang = Language.RU

    @Test
    fun `total coincidence`() {
        val length = 4
        val hiddenWord = listOf('w', 'o', 'r', 'd')
        val word = listOf('w', 'o', 'r', 'd')
        val expectedAnswer = arrayOf(
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
            callback = object : UseCaseCallback<CheckWordUseCase.ErrorType, Array<Cell>> {

                override fun onError(error: CheckWordUseCase.ErrorType) {
                    //NOP
                }

                override fun onSuccess(result: Array<Cell>) {
                    println("actual: " + result.contentToString())
                    println("expected: " +  expectedAnswer.contentToString())
                    assertEquals(result, expectedAnswer)
                }
            })
    }

    @Test
    fun `partial coincidence`() {
        val length = 4
        val hiddenWord = listOf('w', 'o', 'r', 'd')
        val word = listOf('c', 'o', 'a', 'r')
        val expectedAnswer = arrayOf(
            Cell('с', Cell.Status.WRONG),
            Cell('o', Cell.Status.RIGHT),
            Cell('a', Cell.Status.WRONG),
            Cell('r', Cell.Status.PRESENT)
        )
        `when`(repository.isWordPresent(lang, "word")).thenReturn(true)

        useCase.execute(
            lang = lang,
            numberOfLetters = length,
            numberOfRows = 1,
            hiddenWord = hiddenWord,
            word = word,
            callback = object : UseCaseCallback<CheckWordUseCase.ErrorType, Array<Cell>> {

                override fun onError(error: CheckWordUseCase.ErrorType) {
                    //NOP
                }

                override fun onSuccess(result: Array<Cell>) {
                    println("actual: " + result.contentToString())
                    println("expected: " +  expectedAnswer.contentToString())
                    assertEquals(result, expectedAnswer)
                }
            })
    }
}