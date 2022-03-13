package com.vkochenkov.wordlegame.presentation.screen.game

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vkochenkov.wordlegame.data.DELETE_CHAR
import com.vkochenkov.wordlegame.data.ENTER_CHAR
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
        mScreenState.value?.let {
            checkWordUseCase.execute(
                currentLang,
                it.numberOfLetters,
                it.hiddenWord,
                it.currentWord,
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