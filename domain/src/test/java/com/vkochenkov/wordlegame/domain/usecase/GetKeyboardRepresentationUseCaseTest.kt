package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.WordsRepository
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.domain.model.Language
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

class GetKeyboardRepresentationUseCaseTest {

    private val repository = Mockito.mock(WordsRepository::class.java)
    private val useCase = GetKeyboardRepresentationUseCase(repository)
    private val lang = Language.RU

    @Test
    fun `should return list of cells after get chars list`() {
        Mockito.`when`(repository.getKeyboard(lang)).thenReturn(
            listOf(
                listOf('1','2'),
                listOf('3','4')
            )
        )

        val actual = useCase.execute(lang)
        val expected = listOf(
            listOf(Cell('1', Cell.Status.DEFAULT), Cell('2', Cell.Status.DEFAULT)),
            listOf(Cell('3', Cell.Status.DEFAULT), Cell('4', Cell.Status.DEFAULT))
        )
        assertEquals(expected, actual)
    }
}