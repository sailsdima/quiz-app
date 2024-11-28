package com.dp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    onNavigateToGameScreen: () -> Unit,
    onNavigateToSettings: () -> Unit,
) {
    Column {
        Text(text = "Home Screen")

        Button(onClick = onNavigateToGameScreen) {
            Text(text = "Go to Game Screen")
        }

        Button(onClick = onNavigateToSettings) {
            Text(text = "Go to Settings Screen")
        }
    }
}