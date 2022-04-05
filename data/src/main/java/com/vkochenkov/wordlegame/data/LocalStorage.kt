package com.vkochenkov.wordlegame.data

const val DELETE_CHAR = '-'
const val ENTER_CHAR = '='

object LocalStorage {

    val cashedWordsList: List<String>? = null

    val ruKeyboardRepresentation: List<List<Char>> = listOf(
        listOf('й', 'ц', 'у', 'к', 'е', 'н', 'г', 'ш', 'щ', 'з', 'х', 'ъ'),
        listOf('ф', 'ы', 'в', 'а', 'п', 'р', 'о', 'л', 'д', 'ж', 'э'),
        listOf(DELETE_CHAR, 'я', 'ч', 'с', 'м', 'и', 'т', 'ь', 'б', 'ю', ENTER_CHAR)
    )

    val enKeyboardRepresentation: List<List<Char>> = listOf(
        listOf('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'),
        listOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'),
        listOf(DELETE_CHAR, 'z', 'x', 'c', 'v', 'b', 'n', 'm', ENTER_CHAR)
    )
}