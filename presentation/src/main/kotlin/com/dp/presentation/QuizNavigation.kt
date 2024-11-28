package com.dp.presentation

import androidx.navigation.NavHostController

object QuizDestinations {
    const val HOME_ROUTE = "home"
    const val GAME_ROUTE = "game"
    const val SETTINGS_ROUTE = "settings"
}

class QuizNavigationActions(navController: NavHostController) {
    val navigateBack: () -> Unit = {
        navController.popBackStack()
    }

    val navigateToGame: () -> Unit = {
        navController.navigate(QuizDestinations.GAME_ROUTE)
    }

    val navigateToSettings: () -> Unit = {
        navController.navigate(QuizDestinations.SETTINGS_ROUTE)
    }
}