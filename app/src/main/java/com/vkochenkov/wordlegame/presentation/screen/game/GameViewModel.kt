package com.vkochenkov.wordlegame.presentation.screen.game

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vkochenkov.wordlegame.data.DELETE_CHAR
import com.vkochenkov.wordlegame.data.Dao
import com.vkochenkov.wordlegame.data.ENTER_CHAR
import com.vkochenkov.wordlegame.data.RepositoryImpl
import com.vkochenkov.wordlegame.domain.Repository
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.domain.model.Language
import com.vkochenkov.wordlegame.domain.usecase.CheckWordUseCase
import com.vkochenkov.wordlegame.domain.usecase.GetKeyboardRepresentationUseCase
import com.vkochenkov.wordlegame.domain.usecase.GetRandomWordUseCase

class GameViewModel(
    private val repository: Repository,
    private val checkWordUseCase: CheckWordUseCase,
    private val getRandomWordUseCase: GetRandomWordUseCase,
    private val getKeyboardRepresentationUseCase: GetKeyboardRepresentationUseCase
) : ViewModel() {

    //todo вынести в шеред префересы в data
    private val currentLang = Language.RU

    private val initialState = GameState(
        hiddenWord = getRandomWordUseCase.execute(currentLang, DEFAULT_NUMBER_OF_LETTERS),
        keyboard = getKeyboardRepresentationUseCase.execute(currentLang)
    )

    private val mScreenState = MutableLiveData(initialState)
    val screenState: LiveData<GameState> = mScreenState


    fun onKeyPressed(context: Context, cell: Cell) {
        cell.letter?.let { char ->
            when (char) {
                DELETE_CHAR -> {
                    //todo
                }
                ENTER_CHAR -> {
                    onCheckPressed(context)
                }
                else -> {
                    //todo
                }
            }
        }
    }

    private fun onCheckPressed(context: Context) {
        screenState.value?.let {
            checkWordUseCase.execute(
                currentLang,
                it.numberOfLetters,
                it.hiddenWord,
                it.currentTippedWord,
                object : CheckWordUseCase.Callback {

                    override fun onError(error: CheckWordUseCase.ErrorType) {
                        Toast.makeText(context, error.name, Toast.LENGTH_SHORT).show()
                    }

                    override fun onSuccess(list: List<Cell>) {
                        TODO("Not yet implemented")
                    }
                }
            )
        }
    }
}