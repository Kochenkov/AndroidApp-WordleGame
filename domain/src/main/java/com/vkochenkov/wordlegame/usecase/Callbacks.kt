package com.vkochenkov.wordlegame.usecase

interface ExecutionCallback<E, R> {

    fun onError(error: E)

    fun onSuccess(result: R)
}

interface ResultCallback<R> {

    fun onResult(result: R)
}