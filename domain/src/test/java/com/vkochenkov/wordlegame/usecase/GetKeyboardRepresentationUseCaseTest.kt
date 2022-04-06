package com.vkochenkov.wordlegame.usecase

import com.vkochenkov.wordlegame.WordsRepository
import com.vkochenkov.wordlegame.model.Cell
import com.vkochenkov.wordlegame.model.Language
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

class GetKeyboardRepresentationUseCaseTest {

    private val repository = Mockito.mock(WordsRepository::class.java)
    private val useCase = GetKeyboardRepresentationUseCase(repository)

    @Test
    fun `should return list of cells after get chars list`() {
        Mockito.`when`(repository.getKeyboard()).thenReturn(
            listOf(
                listOf('1','2'),
                listOf('3','4')
            )
        )

        val actual = useCase.execute()
        val expected = listOf(
            listOf(Cell('1', Cell.Status.DEFAULT), Cell('2', Cell.Status.DEFAULT)),
            listOf(Cell('3', Cell.Status.DEFAULT), Cell('4', Cell.Status.DEFAULT))
        )
        assertEquals(expected, actual)
    }
}