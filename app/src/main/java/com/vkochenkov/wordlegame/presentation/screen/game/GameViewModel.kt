package com.vkochenkov.wordlegame.presentation.screen.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vkochenkov.wordlegame.data.Dao
import com.vkochenkov.wordlegame.data.RepositoryImpl
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.domain.usecase.CheckWordUseCase
import com.vkochenkov.wordlegame.domain.usecase.GetRandomWordUseCase

class GameViewModel : ViewModel() {

    //todo use di
    val repository = RepositoryImpl(Dao())
    val checkWordUseCase = CheckWordUseCase(repository)
    val getRandomWordUseCase = GetRandomWordUseCase(repository)

    private val initialState = GameState(
        hiddenWord = getRandomWordUseCase.execute(DEFAULT_NUMBER_OF_LETTERS)
    )
    private val mScreenState = MutableLiveData(initialState)
    val screenState: LiveData<GameState> = mScreenState

    fun onCheckPressed() {
        screenState.value?.let {
            checkWordUseCase.execute(
                it.numberOfLetters,
                it.hiddenWord,
                it.currentTippedWord,
                object : CheckWordUseCase.Callback {

                    override fun onError(error: CheckWordUseCase.ErrorType) {
                        TODO("Not yet implemented")
                    }

                    override fun onSuccess(list: List<Cell>) {
                        TODO("Not yet implemented")
                    }
                }
            )
        }
    }
}