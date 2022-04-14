package com.vkochenkov.wordlegame.presentation.screen.home

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.vkochenkov.wordlegame.presentation.MainActivity
import com.vkochenkov.wordlegame.presentation.NavigationRoute

class HomeViewModel : ViewModel() {

    fun onStartGamePressed(navController: NavController) {
        MainActivity.isNewGame = true
        navController.navigate(NavigationRoute.GAME.name)
    }

    fun onContinueGamePressed(navController: NavController) {
        MainActivity.isNewGame = false
        navController.navigate(NavigationRoute.GAME.name)
    }

    fun onSettingsPressed(navController: NavController) {
        MainActivity.isNewGame = false
        navController.navigate(NavigationRoute.SETTINGS.name)
    }

    fun onBackPressed(context: Context) {
        if (context is Activity) {
            context.onBackPressed()
        }
    }
}