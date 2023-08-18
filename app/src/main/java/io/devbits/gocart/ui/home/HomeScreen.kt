package io.devbits.gocart.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.devbits.gocart.composeui.theme.GoCartTheme

@Composable
fun HomeRoute(modifier: Modifier) {
    HomeScreen(modifier)
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Text(text = "Home Screen", modifier = modifier)
}

@Preview
@Composable
fun HomeScreenPreview() {
    GoCartTheme {
        HomeScreen()
    }
}