package com.vkochenkov.wordlegame.presentation.screen.game

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkochenkov.wordlegame.data.DELETE_CHAR
import com.vkochenkov.wordlegame.data.ENTER_CHAR
import com.vkochenkov.wordlegame.domain.Repository
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.domain.model.Language
import com.vkochenkov.wordlegame.domain.usecase.CheckWordUseCase
import com.vkochenkov.wordlegame.domain.usecase.GetKeyboardRepresentationUseCase
import com.vkochenkov.wordlegame.domain.usecase.GetRandomWordUseCase
import com.vkochenkov.wordlegame.domain.usecase.UseCaseCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(
    private val checkWordUseCase: CheckWordUseCase,
    private val getRandomWordUseCase: GetRandomWordUseCase,
    private val getKeyboardRepresentationUseCase: GetKeyboardRepresentationUseCase
) : ViewModel() {

    //todo вынести в шеред префересы в data
    private val currentLang = Language.RU

    private val initialState = GameState(
        language = currentLang,
        hiddenWord = getRandomWordUseCase.execute(currentLang, DEFAULT_NUMBER_OF_LETTERS),
        keyboard = getKeyboardRepresentationUseCase.execute(currentLang)
    )

    private val mScreenState = MutableLiveData(initialState)
    val screenState: LiveData<GameState> = mScreenState

    fun onKeyPressed(context: Context, cell: Cell) {
        cell.letter?.let { char ->
            when (char) {
                DELETE_CHAR -> deleteLetter()
                ENTER_CHAR -> checkEnter(context)
                else -> addLetter(char)
            }
        }
    }

    private fun deleteLetter() {
        mScreenState.value?.let { state ->
            if (state.currentWord.isNotEmpty()) {
                val newWord = state.currentWord.toMutableList()
                newWord.removeAt(newWord.size - 1)
                val newBoard = state.board
                for (i in newBoard.indices) {
                    if (i == state.currentRow) {
                        newBoard[i][state.currentWord.size - 1] = Cell(null)
                        break
                    }
                }
                val newState = state.copy(
                    currentWord = newWord,
                    board = newBoard
                )
                mScreenState.postValue(newState)
            }
        }
    }

    private fun addLetter(char: Char) {
        mScreenState.value?.let { state ->
            if (state.currentWord.size < state.numberOfLetters) {

                val newWord = state.currentWord.toMutableList()
                newWord.add(char)

                val newBoard = state.board
                for (i in newBoard.indices) {
                    if (i == state.currentRow) {
                        newBoard[i][newWord.size - 1] = Cell(char)
                        break
                    }
                }
                val newState = state.copy(
                    currentWord = newWord,
                    board = newBoard
                )
                mScreenState.postValue(newState)
            }
        }
    }

    private fun checkEnter(context: Context) {
        mScreenState.value?.let { state ->
            viewModelScope.launch(Dispatchers.IO) {
                checkWordUseCase.execute(
                    state.language,
                    state.numberOfLetters,
                    state.numberOfRows,
                    state.hiddenWord,
                    state.currentWord,
                    object : UseCaseCallback<CheckWordUseCase.ErrorType, Array<Cell>> {

                        override fun onError(error: CheckWordUseCase.ErrorType) {
                            viewModelScope.launch(Dispatchers.Main) {
                                Toast.makeText(context, error.name, Toast.LENGTH_SHORT).show()
                            }

                        }

                        override fun onSuccess(result: Array<Cell>) {
                            viewModelScope.launch(Dispatchers.Main) {
                                val newBoard = state.board
                                newBoard[state.currentRow] = result
                                val newState = state.copy(
                                    board = newBoard,
                                    currentRow = state.currentRow+1,
                                    currentWord = listOf(),
                                )
                                mScreenState.postValue(newState)
                            }
                        }
                    }
                )
            }
        }
    }
}