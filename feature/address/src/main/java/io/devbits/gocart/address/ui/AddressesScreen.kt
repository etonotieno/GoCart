package io.devbits.gocart.address.ui

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
fun AddressesScreen(
    modifier: Modifier = Modifier,
    viewModel: AddressViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    AddressesScreen(modifier = modifier, state = state)
}

@Composable
fun AddressesScreen(modifier: Modifier = Modifier, state: String) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = state)
    }
}

@Preview
@Composable
private fun AddressesScreenPreview() {
    GoCartTheme {
        AddressesScreen(state = "My Addresses")
    }
}
