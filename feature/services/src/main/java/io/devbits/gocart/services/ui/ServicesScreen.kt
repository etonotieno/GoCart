package io.devbits.gocart.services.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.devbits.gocart.designsystem.theme.GoCartTheme

@Composable
fun ServicesScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Services")
    }
}

@Preview
@Composable
fun ServicesScreenPreview() {
    GoCartTheme {
        ServicesScreen()
    }
}
