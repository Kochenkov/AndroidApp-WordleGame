package com.vkochenkov.wordlegame.domain.usecase

import com.vkochenkov.wordlegame.domain.Repository
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CheckWordUseCaseTest {

    private val repository = mock(Repository::class.java)

    @Test
    fun test() {
        //`when`(repository.isWordPresent("someWord")).thenReturn(true)

        //useCase.execute("someWord")
    }
}