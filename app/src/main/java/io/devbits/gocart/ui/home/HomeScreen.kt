package io.devbits.gocart.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.devbits.gocart.composeui.theme.GoCartTheme

@Composable
fun HomeScreen() {
    Text(text = "Home Screen")
}

@Preview
@Composable
fun HomeScreenPreview() {
    GoCartTheme {
        HomeScreen()
    }
}