package com.vkochenkov.wordlegame.presentation.screen.settings

import com.vkochenkov.wordlegame.model.Language

data class SettingsState(
    val language: Language,
    val length: Int
)
