package io.devbits.gocart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import io.devbits.gocart.composeui.theme.GoCartTheme
import io.devbits.gocart.navigation.GoCartNavHost

class GoCartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            GoCartApp()
        }
    }
}

@Composable
fun GoCartApp() {
    GoCartTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            GoCartNavHost()
        }
    }
}

@Preview
@Composable
fun GoCartAppPreview() {
    GoCartTheme {
        GoCartApp()
    }
}