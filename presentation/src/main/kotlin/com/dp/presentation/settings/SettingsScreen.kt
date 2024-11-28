package com.dp.presentation.settings

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(
    navigateBack: () -> Unit
) {
    Button(onClick = navigateBack) {
        Text(text = "Go Back")
    }
    Text(text = "Settings Screen")
}