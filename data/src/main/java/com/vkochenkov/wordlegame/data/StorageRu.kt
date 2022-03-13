package com.vkochenkov.wordlegame.data

object StorageRu {

    val wordsList5: List<String> = listOf("книга", "полка", "майка", "птица")

    // '-' means delete, '=' means enter
    val keyboardRepresentation: List<List<Char>> = listOf(
        listOf('й','ц','у','к','е','н','г','ш','щ','з','х','ъ'),
        listOf('ф','ы','в','а','п','р','о','л','д','ж','э'),
        listOf('-','я','ч','с','м','и','т','ь','б','ю','=')
    )
}