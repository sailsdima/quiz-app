package com.dp.presentation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.dp.presentation.theme.AppTheme

@Composable
fun QuizApp() {
    AppTheme {
        val navController = rememberNavController()

        QuizNavGraph(
            navController = navController,
            modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars)
        )
    }
}