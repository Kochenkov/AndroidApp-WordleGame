package com.vkochenkov.wordlegame.presentation.screen.game

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vkochenkov.wordlegame.KeyboardsStorage.Companion.DELETE_CHAR
import com.vkochenkov.wordlegame.KeyboardsStorage.Companion.ENTER_CHAR
import com.vkochenkov.wordlegame.R
import com.vkochenkov.wordlegame.model.Cell
import com.vkochenkov.wordlegame.model.GameStatus
import com.vkochenkov.wordlegame.presentation.theme.*
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
            modifier = Modifier.fillMaxSize()
                .background(
                color = WordleTheme.colors.background
            ),
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
        backgroundColor = when (cell.status) {
            Cell.Status.PRESENT -> Yellow
            Cell.Status.WRONG -> WordleTheme.colors.wrongCell
            Cell.Status.RIGHT -> Green
            else -> WordleTheme.colors.emptyCell
        },
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .border(BorderStroke(2.dp, WordleTheme.colors.button), MaterialTheme.shapes.medium)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            if (cell.letter != null) {
                Text(
                    fontSize = 30.sp,
                    text = cell.letter.toString(),
                    color = WordleTheme.colors.content
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

    MainButton(
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
            .height(50.dp)
            .padding(horizontal = 1.dp, vertical = 2.dp),
        onClick = {
            viewModel.onKeyPressed(context, cell)
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = when (cell.status) {
                Cell.Status.PRESENT -> Yellow
                Cell.Status.WRONG -> WordleTheme.colors.wrongCell
                Cell.Status.RIGHT -> Green
                else -> WordleTheme.colors.button
            },
            contentColor = WordleTheme.colors.content
        )
    ) {
        if (cell.letter != null) {
            when (cell.letter) {
                DELETE_CHAR -> {
                    Icon(
                        painter = painterResource(R.drawable.ic_baseline_backspace_24),
                        contentDescription = stringResource(R.string.delete),
                    )
                }
                ENTER_CHAR -> {
                    Icon(
                        painter = painterResource(R.drawable.ic_baseline_enter_24),
                        contentDescription = stringResource(R.string.enter),
                    )
                }
                else -> {
                    Text(
                        fontSize = 20.sp,
                        text = cell.letter.toString(),
                    )
                }
            }
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
            backgroundColor = WordleTheme.colors.background,
            onDismissRequest = {
                viewModel.onBackPressed(navController, false)
            },
            title = {
                val text = when (gameStatus) {
                    GameStatus.VICTORY -> stringResource(R.string.victory)
                    GameStatus.LOSE -> stringResource(R.string.lose)
                    else -> ""
                }
                Text(
                    text = text,
                    color = WordleTheme.colors.content
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.word_to_guess) + " " + hiddenWord,
                    color = WordleTheme.colors.content
                )
            },
            confirmButton = {
                MainButton(
                    onClick = {
                        viewModel.onNewGame()
                    }) {
                    Text(stringResource(R.string.start_game))
                }
            },
            dismissButton = {
                MainButton(
                    onClick = {
                        viewModel.onBackPressed(navController, false)
                    }) {
                    Text(stringResource(R.string.exit))
                }
            }
        )
    }
}