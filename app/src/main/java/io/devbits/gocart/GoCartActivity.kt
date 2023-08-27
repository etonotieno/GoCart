package io.devbits.gocart

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.currentBackStackEntryAsState
import io.devbits.gocart.core.datastore.UserPreferences
import io.devbits.gocart.data.dataStore
import io.devbits.gocart.ui.GoCartApp
import io.devbits.gocart.ui.GoCartAppState
import io.devbits.gocart.ui.rememberGoCartAppState

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

        enableEdgeToEdge()

        setContent {
            val appState = rememberGoCartAppState(preferences = preferences)

            val statusBarStyle = statusBarStyle(appState)
            val navBarStyle = navBarStyle(appState)

            val backStack by appState.navController.currentBackStackEntryAsState()
            DisposableEffect(backStack) {
                enableEdgeToEdge(
                    statusBarStyle = statusBarStyle,
                    navigationBarStyle = navBarStyle,
                )
                onDispose {}
            }

            GoCartApp(
                startDestination = viewModel.startDestination.value,
                appState = appState,
            )
        }
    }

    @Composable
    private fun navBarStyle(appState: GoCartAppState) =
        if (appState.isAuthenticationScreen) {
            SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT,
            ) { true }
        } else {
            SystemBarStyle.auto(
                lightScrim = lightScrim,
                darkScrim = lightScrim,
            ) { false }
        }

    @Composable
    private fun statusBarStyle(appState: GoCartAppState) =
        if (appState.isAuthenticationScreen) {
            SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT,
            ) { true }
        } else {
            SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT,
            ) { false }
        }

}

/**
 * The default light scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val lightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val darkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)
