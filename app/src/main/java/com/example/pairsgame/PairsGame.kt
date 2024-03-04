package com.example.pairsgame

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pairsgame.ui.screens.EndGameScreen
import com.example.pairsgame.ui.screens.GameSceneScreen
import com.example.pairsgame.ui.screens.MenuScreen

enum class PairsGameScreen(@StringRes val title: Int) {
    Menu(R.string.app_name),
    GameScene(R.string.game_scene),
    EndGame(R.string.end_game),
}

@Composable
fun PairsGame(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = PairsGameScreen.valueOf(
        backStackEntry?.destination?.route ?: PairsGameScreen.Menu.name
    )

    NavHost(
        navController = navController,
        startDestination = PairsGameScreen.Menu.name,
    ) {
        composable(route = PairsGameScreen.Menu.name) {
            MenuScreen(
                onPlayButtonClicked = {
                    navController.navigate(PairsGameScreen.GameScene.name)
                }
            )
        }
        composable(route = PairsGameScreen.GameScene.name) {
            GameSceneScreen(navController = navController)
        }
        composable(route = PairsGameScreen.EndGame.name) {
            EndGameScreen(
                onHomeButtonClicked = {
                    Container.budget += Container.prize
                    navController.navigate(PairsGameScreen.Menu.name)
                }
            )
        }
    }
}