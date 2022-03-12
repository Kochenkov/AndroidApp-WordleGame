package com.vkochenkov.wordlegame.presentation.screen.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
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
import com.vkochenkov.wordlegame.presentation.screen.home.HomeViewModel

@Composable
fun GameScreen() {

    val viewModel = viewModel<GameViewModel>()
    val screenState by viewModel.screenState.observeAsState()
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(6) {
            LazyRow {
                items(5) {
                    Cell()
                }
            }
        }
    }
}

@Composable
fun Cell() {
    Card(
        backgroundColor = Color.Red,
        modifier = Modifier.size(50.dp)
            .padding(4.dp),
    ) {

    }
}