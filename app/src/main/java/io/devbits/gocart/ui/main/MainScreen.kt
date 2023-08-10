package io.devbits.gocart.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.devbits.gocart.composeui.theme.GoCartTheme
import io.devbits.gocart.navigation.GoCartNavHost

@Composable
fun MainScreen() {
    GoCartNavHost()
}

@Preview
@Composable
fun MainScreenPreview() {
    GoCartTheme {
        MainScreen()
    }
}