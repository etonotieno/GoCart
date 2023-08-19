package io.devbits.gocart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import io.devbits.gocart.composeui.theme.GoCartTheme
import io.devbits.gocart.core.data.UserPreferences
import io.devbits.gocart.core.data.dataStore
import io.devbits.gocart.navigation.GoCartNavHost
import io.devbits.gocart.ui.MainViewModel

class GoCartActivity : ComponentActivity() {

    private lateinit var preferences: UserPreferences

    private val viewModel: MainViewModel by viewModels {
        viewModelFactory {
            initializer {
                MainViewModel(preferences = preferences)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        preferences = UserPreferences(dataStore = dataStore)
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.showSplashScreen.value }
        }

        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            GoCartApp(viewModel.startDestination.value, preferences)
        }
    }
}

@Composable
fun GoCartApp(
    startDestination: String,
    preferences: UserPreferences,
) {
    GoCartTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            GoCartNavHost(
                startDestination = startDestination,
                preferences = preferences,
            )
        }
    }
}
