package com.vkochenkov.wordlegame.presentation.screen.home

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.vkochenkov.wordlegame.presentation.NavigationRoute

class HomeViewModel : ViewModel() {

    private val initialState = HomeState()
    private val mScreenState = MutableLiveData(initialState)
    val screenState: LiveData<HomeState> = mScreenState

    fun onStartGamePressed(navController: NavController) {
        val newState = mScreenState.value?.copy(
            continueGame = true
        )
        mScreenState.postValue(newState)
        navController.navigate(NavigationRoute.GAME.name)
    }

    fun onExitPressed(context: Context) {
        if (context is Activity) {
            context.onBackPressed()
        }
    }
}