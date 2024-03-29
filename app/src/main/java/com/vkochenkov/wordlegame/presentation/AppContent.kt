package com.vkochenkov.wordlegame.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vkochenkov.wordlegame.presentation.screen.game.GameScreen
import com.vkochenkov.wordlegame.presentation.screen.home.HomeScreen
import com.vkochenkov.wordlegame.presentation.screen.settings.SettingsScreen
import com.vkochenkov.wordlegame.presentation.theme.WordleTheme

@Composable
fun AppContent() {

    val navController = rememberNavController()

    WordleTheme {
        Surface(color = WordleTheme.colors.background) {
            Scaffold { paddingValues ->
                NavHost(
                    navController = navController,
                    startDestination = NavigationRoute.HOME.name,
                    modifier = Modifier.padding(paddingValues)
                ) {
                    composable(NavigationRoute.HOME.name) {
                        HomeScreen(navController)
                    }
                    composable(NavigationRoute.GAME.name) {
                        GameScreen(navController)
                    }
                    composable(NavigationRoute.SETTINGS.name) {
                        SettingsScreen(navController)
                    }
                    composable(NavigationRoute.ABOUT.name) {
                       // GameScreen(navController)
                    }
                }
            }
        }
    }
}