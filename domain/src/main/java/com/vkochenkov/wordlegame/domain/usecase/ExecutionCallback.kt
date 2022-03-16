package com.vkochenkov.wordlegame.domain.usecase

interface ExecutionCallback<E, R> {

    fun onError(error: E)

    fun onSuccess(result: R)
}