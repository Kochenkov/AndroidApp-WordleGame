package com.vkochenkov.wordlegame.domain.usecase

interface UseCaseCallback<E, R> {

    fun onError(error: E)

    fun onSuccess(result: R)
}