package com.vkochenkov.wordlegame.presentation.screen.game

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vkochenkov.wordlegame.R
import com.vkochenkov.wordlegame.data.DELETE_CHAR
import com.vkochenkov.wordlegame.data.ENTER_CHAR
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.domain.model.GameStatus
import com.vkochenkov.wordlegame.presentation.NavigationRoute
import com.vkochenkov.wordlegame.presentation.theme.Gray
import com.vkochenkov.wordlegame.presentation.theme.Green
import com.vkochenkov.wordlegame.presentation.theme.Whiter
import com.vkochenkov.wordlegame.presentation.theme.Yellow
import com.vkochenkov.wordlegame.util.toCorrectString
import org.koin.androidx.compose.getViewModel

@Composable
fun GameScreen(
    navController: NavController
) {

    val viewModel = getViewModel<GameViewModel>()
    val screenState by viewModel.screenState.observeAsState()

    LaunchedEffect(true) {
        viewModel.onGameStarted()
    }

    screenState?.apply {
        BackHandler(true) {
            viewModel.onBackPressed(navController)
        }

        EndGameDialog(viewModel, gameStatus, hiddenWord.toCorrectString(), navController)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Column(Modifier.padding(16.dp)) {
                board.forEach { line ->
                    Row {
                        line.forEach {
                            BoardCell(it, Modifier.weight(1f))
                        }
                    }

                }
            }

            Column(Modifier.padding(2.dp)) {
                keyboard.forEach { line ->
                    Row() {
                        line.forEach { cell ->
                            var modifier = Modifier.weight(1f)

                            cell.letter?.let { char ->
                                if (char == DELETE_CHAR || char == ENTER_CHAR) {
                                    modifier = Modifier.weight(1.5f)
                                }
                            }

                            KeyboardButton(viewModel, cell, modifier)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BoardCell(
    cell: Cell,
    modifier: Modifier
) {
    Card(
        backgroundColor =
        when (cell.status) {
            Cell.Status.PRESENT -> Yellow
            Cell.Status.WRONG -> Gray
            Cell.Status.RIGHT -> Green
            Cell.Status.DEFAULT -> Whiter
            Cell.Status.PREFILL -> Whiter
        },
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .border(BorderStroke(2.dp, Color.Black), MaterialTheme.shapes.medium)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            if (cell.letter != null) {
                Text(
                    fontSize = 30.sp,
                    text = cell.letter.toString(),
                )
            }
        }
    }
}

@Composable
private fun KeyboardButton(
    viewModel: GameViewModel,
    cell: Cell,
    modifier: Modifier
) {
    val context = LocalContext.current

    Button(
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
            .height(50.dp)
            .padding(horizontal = 1.dp, vertical = 2.dp),
        onClick = {
            viewModel.onKeyPressed(context, cell)
        }
    ) {
        if (cell.letter != null) {
            Text(
                fontSize = 20.sp,
                text = cell.letter.toString(),
            )
        }
    }
}

@Composable
private fun EndGameDialog(
    viewModel: GameViewModel,
    gameStatus: GameStatus,
    hiddenWord: String,
    navController: NavController
) {

    if (gameStatus == GameStatus.VICTORY || gameStatus == GameStatus.LOSE) {
        AlertDialog(
            onDismissRequest = {
                viewModel.onBackPressed(navController)
            },
            title = {
                val text = when (gameStatus) {
                    GameStatus.VICTORY -> stringResource(R.string.victory)
                    GameStatus.LOSE -> stringResource(R.string.lose)
                    else -> ""
                }
                Text(text)
            },
            text = {
                Text(stringResource(R.string.word_to_guess) + " " + hiddenWord)
            },
            confirmButton = {
                Button(
                    onClick = {

                    }) {
                    Text("This is the Confirm Button")
                }
            },
            dismissButton = {
                Button(
                    onClick = {

                    }) {
                    Text("This is the dismiss Button")
                }
            }
        )
    }
}