package io.devbits.gocart.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.devbits.gocart.designsystem.theme.GoCartTheme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    SettingsScreen(modifier = modifier, state = state)
}

@Composable
fun SettingsScreen(modifier: Modifier = Modifier, state: String) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = state)
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    GoCartTheme {
        SettingsScreen(state = "")
    }
}
