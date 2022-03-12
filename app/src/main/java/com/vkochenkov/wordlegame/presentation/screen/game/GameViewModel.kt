package com.vkochenkov.wordlegame.presentation.screen.game

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.vkochenkov.wordlegame.presentation.NavigationRoute

class GameViewModel : ViewModel() {

    private val initialState = GameState(
        hiddenWord = listOf('к','н','и','г','а')
    )
    private val mScreenState = MutableLiveData(initialState)
    val screenState: LiveData<GameState> = mScreenState

//    fun onStartGamePressed(navController: NavController) {
//        val newState = mScreenState.value?.copy(
//            continueGame = true
//        )
//        mScreenState.postValue(newState)
//        navController.navigate(NavigationRoute.GAME.name)
//    }
//
//    fun onExitPressed(context: Context) {
//        if (context is Activity) {
//            context.onBackPressed()
//        }
//    }
}