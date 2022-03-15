package com.vkochenkov.wordlegame.data

const val DELETE_CHAR = '-'
const val ENTER_CHAR = '='

object StorageRu {

    val wordsList5: List<String> = listOf("книга", "полка", "майка", "птица", "ларек")

    // '-' means delete, '=' means enter
    val keyboardRepresentation: List<List<Char>> = listOf(
        listOf('й', 'ц', 'у', 'к', 'е', 'н', 'г', 'ш', 'щ', 'з', 'х', 'ъ'),
        listOf('ф', 'ы', 'в', 'а', 'п', 'р', 'о', 'л', 'д', 'ж', 'э'),
        listOf(DELETE_CHAR, 'я', 'ч', 'с', 'м', 'и', 'т', 'ь', 'б', 'ю', ENTER_CHAR)
    )
}