package com.vkochenkov.wordlegame.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class WordleColors(
    val background: Color,
    val emptyCell: Color,
    val wrongCell: Color,
    val button: Color,
    val content: Color
)

object WordleTheme {
    val colors: WordleColors
        @Composable
        get() = LocalWordleColors.current
}

val LocalWordleColors = staticCompositionLocalOf<WordleColors> {
    error("No colors provided")
}

private val DarkColorPalette = WordleColors(
    background = Dark,
    emptyCell = LightDark,
    wrongCell = DarkerGray,
    button = DarkGray,
    content = White
)

private val LightColorPalette = WordleColors(
    background = White,
    emptyCell = DarkWhite,
    wrongCell = Gray,
    button = LightGray,
    content = Dark
)

/* Other default colors to override
background = Color.White,
surface = Color.White,
onPrimary = Color.White,
onSecondary = Color.Black,
onBackground = Color.Black,
onSurface = Color.Black,
*/

@Composable
fun WordleTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(
        LocalWordleColors provides colors,
        content = content
    )
}