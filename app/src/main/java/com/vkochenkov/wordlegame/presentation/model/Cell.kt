package com.vkochenkov.wordlegame.presentation.model

data class Cell(
    val status: Status
) {
    sealed class Status {

        //no entered letters
        object Empty : Status()

        //entered letters without validation
        data class Prefill(val letter: Char) : Status()

        //validated -> present but not in right place
        data class Present(val letter: Char) : Status()

        //validated -> in exact right place
        data class Right(val letter: Char) : Status()

        //validated -> no contains that letter
        data class Wrong(val letter: Char) : Status()
    }
}