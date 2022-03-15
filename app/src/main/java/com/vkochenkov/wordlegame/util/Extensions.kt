package com.vkochenkov.wordlegame.util

fun List<Char>.toCorrectString(): String {
    return this.toString()
        .replace(", ", "")
        .replace("[", "")
        .replace("]", "")
}
