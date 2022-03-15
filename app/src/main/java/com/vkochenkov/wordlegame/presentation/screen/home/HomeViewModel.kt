package com.vkochenkov.wordlegame.presentation.screen.home

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.vkochenkov.wordlegame.domain.model.GameStatus
import com.vkochenkov.wordlegame.presentation.NavigationRoute

class HomeViewModel : ViewModel() {

    private val initialState = HomeState(GameStatus.NONE)
    private val mScreenState = MutableLiveData(initialState)
    val screenState: LiveData<HomeState> = mScreenState

    fun onStartGamePressed(navController: NavController) {
        val newState = mScreenState.value?.copy(
            lastGameStatus = GameStatus.PLAYING
        )
        mScreenState.postValue(newState)
        navController.navigate(NavigationRoute.GAME.name)
    }

    fun onBackPressed(context: Context) {
        if (context is Activity) {
            context.onBackPressed()
        }
    }

    fun onContinueGamePressed(navController: NavController) {
        //todo save?
        val newState = mScreenState.value?.copy(
            lastGameStatus = GameStatus.PLAYING
        )
        mScreenState.postValue(newState)
        navController.navigate(NavigationRoute.GAME.name)
    }
}