package io.devbits.gocart.composeui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.devbits.gocart.composeui.theme.white

/**
 * Helper composable function to display edge-to-edge content
 *
 * @param themed Whether the system bars should be themed or transparent
 */
@Composable
fun SystemBars(themed: Boolean) {
    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController, themed) {
        systemUiController.setSystemBarsColor(
            color = if (themed) white else Color.Transparent,
            darkIcons = themed
        )

        onDispose {}
    }
}