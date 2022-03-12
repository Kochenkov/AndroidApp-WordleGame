package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.Repository
import junit.framework.TestCase
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CheckWordUseCaseTest : TestCase() {

    private val repository = mock(Repository::class.java)
    private val useCase = CheckWordUseCase(repository)

    fun test() {
        `when`(useCase.execute("someWord")).thenReturn(true)

        assertTrue(useCase.execute("someWord"))
        assertFalse(useCase.execute("anotherWord"))
    }
}