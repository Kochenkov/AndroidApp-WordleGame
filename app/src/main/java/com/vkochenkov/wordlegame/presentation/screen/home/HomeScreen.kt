package com.vkochenkov.wordlegame.presentation.screen.home

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController
) {
    Text(text = "home screen")
    Button(onClick = {
        navController.navigate("game")
    }) {

    }
}