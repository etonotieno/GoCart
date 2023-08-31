package io.devbit.gocart.orders.ui

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
fun OrdersScreen(modifier: Modifier = Modifier, viewModel: OrdersViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    OrdersScreen(modifier = modifier, state = state)
}

@Composable
fun OrdersScreen(modifier: Modifier = Modifier, state: String) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = state)
    }
}

@Preview
@Composable
fun OrdersScreenPreview() {
    GoCartTheme {
        OrdersScreen(state = "")
    }
}
