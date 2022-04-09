package com.vkochenkov.wordlegame.presentation.screen.game

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.vkochenkov.wordlegame.KeyboardsStorage.Companion.DELETE_CHAR
import com.vkochenkov.wordlegame.KeyboardsStorage.Companion.ENTER_CHAR
import com.vkochenkov.wordlegame.R
import com.vkochenkov.wordlegame.Repository
import com.vkochenkov.wordlegame.model.Cell
import com.vkochenkov.wordlegame.model.GameStatus
import com.vkochenkov.wordlegame.presentation.MainActivity
import com.vkochenkov.wordlegame.presentation.NavigationRoute
import com.vkochenkov.wordlegame.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(
    private val wordValidationUseCase: WordValidationUseCase,
    private val addLetterUseCase: AddLetterUseCase,
    private val deleteLetterUseCase: DeleteLetterUseCase,
    private val repository: Repository
) : ViewModel() {

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
            deleteLetterUseCase.execute(
                currentWord = state.currentWord,
                callback = object : ResultCallback<List<Char>> {
                    override fun onResult(result: List<Char>) {
                        val newBoard = state.board
                        newBoard[state.currentRow][result.size] = Cell(null, Cell.Status.DEFAULT)
                        val newState = state.copy(
                            currentWord = result,
                            board = newBoard
                        )
                        mScreenState.postValue(newState)
                    }
                }
            )
        }
    }

    private fun addLetter(char: Char) {
        mScreenState.value?.let { state ->
            addLetterUseCase.execute(
                char = char,
                currentWord = state.currentWord,
                callback = object : ResultCallback<List<Char>> {
                    override fun onResult(result: List<Char>) {
                        val newBoard = state.board
                        newBoard[state.currentRow][result.size-1] = Cell(result[result.lastIndex], Cell.Status.PREFILL)
                        val newState = state.copy(
                            currentWord = result,
                            board = newBoard
                        )
                        mScreenState.postValue(newState)
                    }
                }
            )
        }
    }

    private fun checkEnter(context: Context) {
        mScreenState.value?.let { state ->
            viewModelScope.launch(Dispatchers.IO) {
                wordValidationUseCase.execute(
                    state.hiddenWord,
                    state.currentWord,
                    state.currentRow,
                    object :
                        ExecutionCallback<WordValidationUseCase.ErrorType, WordValidationUseCase.Result> {

                        override fun onError(error: WordValidationUseCase.ErrorType) {
                            viewModelScope.launch(Dispatchers.Main) {
                                val errorName = when (error) {
                                    WordValidationUseCase.ErrorType.NOT_FULL_LINE -> context.getString(
                                        R.string.error_length
                                    )
                                    WordValidationUseCase.ErrorType.DOES_NOT_IN_DB -> context.getString(
                                        R.string.error_db
                                    )
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
            hiddenWord = repository.getRandomWord(),
            keyboard = repository.getKeyboard(),
            board = createBoard()
        )
    }

    private fun createBoard(): Array<Array<Cell>> {
        val numberOfLetters = repository.getLength()
        val numberOfRows = repository.getRows()
        return Array(numberOfRows) { Array(numberOfLetters) { Cell() } }
    }
}
