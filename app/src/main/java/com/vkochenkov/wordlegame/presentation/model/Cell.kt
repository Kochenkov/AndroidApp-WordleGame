package com.vkochenkov.wordlegame.presentation.model

data class Cell(
    val letter: Char? = null,
    val status: Status = Status.EMPTY
) {
    enum class Status {
        EMPTY, //no entered letters
        PREFILL, //entered letters without validation
        PRESENT, //validated -> present but not in right place
        RIGHT, //validated -> in exact right place
        WRONG //validated -> no contains that letter
    }
}