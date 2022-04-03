package com.vkochenkov.wordlegame.presentation.screen.game

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.vkochenkov.wordlegame.R
import com.vkochenkov.wordlegame.data.DELETE_CHAR
import com.vkochenkov.wordlegame.data.ENTER_CHAR
import com.vkochenkov.wordlegame.domain.model.Cell
import com.vkochenkov.wordlegame.domain.model.GameStatus
import com.vkochenkov.wordlegame.domain.model.Language
import com.vkochenkov.wordlegame.domain.usecase.WordValidationUseCase
import com.vkochenkov.wordlegame.domain.usecase.GetKeyboardRepresentationUseCase
import com.vkochenkov.wordlegame.domain.usecase.GetRandomWordUseCase
import com.vkochenkov.wordlegame.domain.usecase.ExecutionCallback
import com.vkochenkov.wordlegame.presentation.MainActivity
import com.vkochenkov.wordlegame.presentation.NavigationRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(
    private val wordValidationUseCase: WordValidationUseCase,
    private val getRandomWordUseCase: GetRandomWordUseCase,
    private val getKeyboardRepresentationUseCase: GetKeyboardRepresentationUseCase
) : ViewModel() {

    //todo вынести в шеред префересы в data
    private val currentLang = Language.RU

    private val mScreenState = MutableLiveData(getInitialState())
    val screenState: LiveData<GameState> = mScreenState

    fun onNewGame() {
        mScreenState.postValue(getInitialState())
    }

    fun onKeyPressed(context: Context, cell: Cell) {
        cell.letter?.let { char ->
            when (char) {
                DELETE_CHAR -> deleteLetter()
                ENTER_CHAR -> checkEnter(context)
                else -> addLetter(char)
            }
        }
    }

    fun onBackPressed(navController: NavController, shouldSaveState: Boolean = true) {
        if (shouldSaveState) {
            val newState = mScreenState.value?.copy(
                gameStatus = GameStatus.PAUSE
            )
            MainActivity.lastGameState = newState
            mScreenState.postValue(newState)
        }
        navController.navigate(NavigationRoute.HOME.name) {
            launchSingleTop = true
            popUpTo(NavigationRoute.GAME.name) { inclusive = true }
        }
    }

    fun onGameStarted() {
        MainActivity.let {
            val newState = if (it.lastGameState != null && !it.isNewGame) {
                it.lastGameState?.copy(
                    gameStatus = GameStatus.PLAYING
                )
            } else {
                mScreenState.value?.copy(
                    gameStatus = GameStatus.PLAYING
                )
            }
            MainActivity.lastGameState = null
            mScreenState.postValue(newState)
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
                wordValidationUseCase.execute(
                    state.language,
                    state.numberOfLetters,
                    state.numberOfRows,
                    state.hiddenWord,
                    state.currentWord,
                    state.currentRow,
                    object :
                        ExecutionCallback<WordValidationUseCase.ErrorType, WordValidationUseCase.Result> {

                        override fun onError(error: WordValidationUseCase.ErrorType) {
                            viewModelScope.launch(Dispatchers.Main) {
                                val errorName = when (error) {
                                    WordValidationUseCase.ErrorType.NOT_FULL_LINE -> context.getString(R.string.error_length)
                                    WordValidationUseCase.ErrorType.DOES_NOT_IN_DB -> context.getString(R.string.error_db)
                                }
                                Toast.makeText(context, errorName, Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onSuccess(result: WordValidationUseCase.Result) {
                            viewModelScope.launch(Dispatchers.Main) {
                                val newBoard = state.board
                                newBoard[state.currentRow] = result.word.toTypedArray()
                                val newState = state.copy(
                                    board = newBoard,
                                    currentRow = state.currentRow + 1,
                                    currentWord = listOf(),
                                    gameStatus = result.gameStatus
                                )
                                mScreenState.postValue(newState)
                            }
                        }
                    }
                )
            }
        }
    }

    private fun getInitialState(): GameState {
        return GameState(
            language = currentLang,
            hiddenWord = getRandomWordUseCase.execute(currentLang, DEFAULT_NUMBER_OF_LETTERS),
            keyboard = getKeyboardRepresentationUseCase.execute(currentLang)
        )
    }
}
