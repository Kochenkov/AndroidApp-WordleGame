package com.vkochenkov.wordlegame.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vkochenkov.wordlegame.presentation.screen.game.GameState

class MainActivity : ComponentActivity() {

    companion object {
        var isNewGame: Boolean = true
        var currentGameState: GameState? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }
}