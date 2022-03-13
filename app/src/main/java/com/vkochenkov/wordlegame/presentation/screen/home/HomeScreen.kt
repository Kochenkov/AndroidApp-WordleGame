package com.vkochenkov.wordlegame.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vkochenkov.wordlegame.R
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    navController: NavController
) {

    val viewModel = getViewModel<HomeViewModel>()
    val screenState by viewModel.screenState.observeAsState()
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
        screenState?.continueGame?.let {
            if (it) {
                item {
                    Item(R.string.continue_game) { }
                }
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
                viewModel.onExitPressed(context)
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