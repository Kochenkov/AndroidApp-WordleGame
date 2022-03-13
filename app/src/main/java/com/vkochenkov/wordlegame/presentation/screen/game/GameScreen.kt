package com.vkochenkov.wordlegame.presentation.screen.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.presentation.theme.Gray
import org.koin.androidx.compose.getViewModel

@Composable
fun GameScreen() {

    val viewModel = getViewModel<GameViewModel>()
    val screenState by viewModel.screenState.observeAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        screenState?.board?.forEach { line ->
            Row {
                line.forEach {
                    BoardCell(it)
                }
            }

        }
        //todo  к низу экрана
        screenState?.keyboard?.forEach { line ->
            Row() {
                line.forEach {
                    KeyboardCell(it, Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun BoardCell(cell: Cell) {
    Card(
        backgroundColor = Gray,
        modifier = Modifier
            .size(50.dp)
            .padding(4.dp)
            .border(BorderStroke(2.dp, Color.Black), MaterialTheme.shapes.medium)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (cell.letter != null) {
                Text(
                    text = cell.letter.toString(),
                )
            }
        }
    }
}

@Composable
fun KeyboardCell(cell: Cell, modifier: Modifier) {
    Card(
        backgroundColor = Gray,
        modifier = modifier
            .height(50.dp)
            .padding(2.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            if (cell.letter != null) {
                Text(
                    text = cell.letter.toString(),
                )
            }
        }
    }
}