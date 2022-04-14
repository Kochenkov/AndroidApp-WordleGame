package com.vkochenkov.wordlegame.presentation.screen.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vkochenkov.wordlegame.Repository
import com.vkochenkov.wordlegame.model.Language
import com.vkochenkov.wordlegame.presentation.MainActivity

class SettingsViewModel(
    private val repository: Repository
) : ViewModel() {

    private val mScreenState = MutableLiveData(getInitialState())
    val screenState: LiveData<SettingsState> = mScreenState

    private fun getInitialState(): SettingsState {
        return SettingsState(
            language = repository.getLanguage(),
            length = repository.getLength()
        )
    }

    fun onLanguageChanged(lang: Language) {
        MainActivity.lastGameState = null
        repository.setLanguage(lang)
        mScreenState.value?.let { state ->
            val newState = state.copy(
                language = lang
            )
            mScreenState.value = newState
        }
    }
}