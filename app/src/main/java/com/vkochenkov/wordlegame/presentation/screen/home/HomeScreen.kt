package com.vkochenkov.wordlegame.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vkochenkov.wordlegame.R
import com.vkochenkov.wordlegame.model.GameStatus
import com.vkochenkov.wordlegame.presentation.MainActivity
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    navController: NavController,
) {

    val viewModel = getViewModel<HomeViewModel>()
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Item(R.string.start_game) { viewModel.onStartGamePressed(navController) }
        }

        if (MainActivity.lastGameState?.gameStatus == GameStatus.PAUSE) {
            item {
                Item(R.string.continue_game) { viewModel.onContinueGamePressed(navController) }
            }
        }

        item {
            Item(R.string.settings) { }
        }
        item {
            Item(R.string.how_to_play) { }
        }
        item {
            Item(R.string.exit) {
                viewModel.onBackPressed(context)
            }
        }
    }
}


@Composable
fun Item(
    textResource: Int,
    action: () -> Unit
) {
    Button(
        onClick = action,
        modifier = Modifier.padding(
            top = 8.dp,
            bottom = 8.dp
        )
    ) {
        Text(stringResource(textResource))
    }
}