package com.vkochenkov.wordlegame.usecase

interface ExecutionCallback<E, R> {

    fun onError(error: E)

    fun onSuccess(result: R)
}