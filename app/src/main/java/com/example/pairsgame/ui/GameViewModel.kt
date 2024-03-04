package com.example.pairsgame.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pairsgame.Container
import com.example.pairsgame.PairsGameScreen
import com.example.pairsgame.data.DataSource
import com.example.pairsgame.data.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class GameState(
    val score: Int = 0,
    val elapsedTime: Int = 0,
    val selectedItems: List<Item> = emptyList(),
    val cells: List<Item> = emptyList(),
    val prize: Int = 0
)

class GameViewModel : ViewModel() {
    private val _gameState = mutableStateOf(GameState()) // Define GameState data class
    val gameState: State<GameState> = _gameState

    private var _navController: NavController? = null


    init {
        // Initialize the game state with the data from DataSource
        _gameState.value = GameState(cells = DataSource.data.shuffled())
    }

    fun setNavController(navController: NavController) {
        _navController = navController
    }

    fun onCellClicked(item: Item) {
        if (!item.checked && _gameState.value.selectedItems.size < 2) {
            updateSelectedItems(item)
            updateCellChecked(item)
            checkForMatch()
        }
    }

    fun updateTimer() {
        _gameState.value = _gameState.value.copy(elapsedTime = _gameState.value.elapsedTime + 1)
    }

    private fun updateSelectedItems(item: Item) {
        _gameState.value = _gameState.value.copy(
            selectedItems = _gameState.value.selectedItems + item
        )
    }

    private fun updateCellChecked(item: Item) {
        _gameState.value = _gameState.value.copy(
            cells = _gameState.value.cells.map {
                if (it.id == item.id && it === item) it.copy(checked = true)
                else it
            }
        )
    }

    private fun checkForMatch() {
        val selectedItems = _gameState.value.selectedItems
        if (selectedItems.size == 2) {
            val (item1, item2) = selectedItems
            delayAndCompareItems(item1, item2)
        }
    }


    private fun delayAndCompareItems(item1: Item, item2: Item) {
        viewModelScope.launch {
            delay(200)

            if (item1.id == item2.id) {
                updateScore(2)
                checkForGameEnd()
                resetSelectedItems()
            } else {
                _gameState.value = _gameState.value.copy(
                    cells = _gameState.value.cells.map {
                        if (it.id == item1.id) it.copy(checked = false)
                        else it
                    }
                )
                _gameState.value = _gameState.value.copy(
                    cells = _gameState.value.cells.map {
                        if (it.id == item2.id) it.copy(checked = false)
                        else it
                    }
                )
                resetSelectedItems()
            }
        }
    }

    private fun updateScore(points: Int) {
        _gameState.value = _gameState.value.copy(score = _gameState.value.score + points)
    }

    private fun checkForGameEnd() {
        if (_gameState.value.score >= 20) {
            _navController?.navigate(PairsGameScreen.EndGame.name)
        }
    }

    private fun resetSelectedItems() {
        _gameState.value = _gameState.value.copy(selectedItems = emptyList())
    }


}