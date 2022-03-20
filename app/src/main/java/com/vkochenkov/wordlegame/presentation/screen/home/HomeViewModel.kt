package com.vkochenkov.wordlegame.presentation.screen.home

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.vkochenkov.wordlegame.domain.model.GameStatus
import com.vkochenkov.wordlegame.presentation.MainActivity
import com.vkochenkov.wordlegame.presentation.NavigationRoute
import com.vkochenkov.wordlegame.presentation.screen.game.GameState

class HomeViewModel : ViewModel() {

    fun onStartGamePressed(navController: NavController) {
        MainActivity.isNewGame = true
        navController.navigate(NavigationRoute.GAME.name)
    }

    fun onContinueGamePressed(navController: NavController) {
        MainActivity.isNewGame = false
        navController.navigate(NavigationRoute.GAME.name)
    }

    fun onBackPressed(context: Context) {
        if (context is Activity) {
            context.onBackPressed()
        }
    }
}