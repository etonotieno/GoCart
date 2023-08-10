package io.devbits.gocart.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.devbits.gocart.composeui.theme.GoCartTheme

@Composable
fun AuthenticationScreen(onExploreApp: () -> Unit) {
    Text(
        text = "Authentication Screen",
        modifier = Modifier
            .fillMaxSize()
            .clickable { onExploreApp() }
    )
}

@Preview
@Composable
fun AuthenticationScreenPreview() {
    GoCartTheme {
        AuthenticationScreen(onExploreApp = {})
    }
}