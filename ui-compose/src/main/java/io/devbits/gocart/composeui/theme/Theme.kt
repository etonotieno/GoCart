package io.devbits.gocart.composeui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val darkColorScheme = darkColorScheme(
    primary = Purple200,
    inversePrimary = Purple700,
    secondary = Teal200
)

private val lightColorScheme = lightColorScheme(
    primary = Purple500,
    inversePrimary = Purple700,
    secondary = Teal200
)

@Composable
fun GoCartTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        darkColorScheme
    } else {
        lightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}