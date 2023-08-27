package io.devbits.gocart.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(modifier: Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Settings")
    }
}
