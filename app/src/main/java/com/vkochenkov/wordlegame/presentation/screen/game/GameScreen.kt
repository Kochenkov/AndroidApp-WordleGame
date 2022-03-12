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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.presentation.theme.Gray

@Composable
fun GameScreen() {

    val viewModel = viewModel<GameViewModel>()
    val screenState by viewModel.screenState.observeAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        screenState?.field?.forEach { field ->
            Row {
                field.forEach {
                    Cell(it)
                }
            }
        }
    }
}

@Composable
fun Cell(cell: Cell) {
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