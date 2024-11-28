package com.dp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dp.presentation.game.GameScreen
import com.dp.presentation.home.HomeScreen
import com.dp.presentation.settings.SettingsScreen

@Composable
fun QuizNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    actions: QuizNavigationActions = QuizNavigationActions(navController),
    startDestination: String = QuizDestinations.HOME_ROUTE,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = QuizDestinations.HOME_ROUTE) {
            HomeScreen(
                onNavigateToGameScreen = actions.navigateToGame,
                onNavigateToSettings = actions.navigateToSettings,
            )
        }
        composable(route = QuizDestinations.GAME_ROUTE) {
            GameScreen(navigateBack = actions.navigateBack)
        }
        composable(route = QuizDestinations.SETTINGS_ROUTE) {
            SettingsScreen(navigateBack = actions.navigateBack)
        }
    }
}