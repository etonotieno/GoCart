package io.devbits.gocart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import io.devbits.gocart.composeui.theme.GoCartTheme
import io.devbits.gocart.ui.main.MainScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            GoCartTheme {
                MainScreen()
            }
        }
    }
}