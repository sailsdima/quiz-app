package com.dp.presentation.game

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun GameScreen(
    navigateBack: () -> Unit
) {
    Button(onClick = navigateBack) {
        Text(text = "Go Back")
    }
    Text(text = "Game Screen")
}