/*
 * Copyright 2023 Eton Otieno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.devbits.gocart

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import io.devbits.gocart.core.datastore.model.AppTheme
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.ui.GoCartApp
import io.devbits.gocart.ui.GoCartAppState
import io.devbits.gocart.ui.rememberGoCartAppState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.showSplashScreen.value }
        }

        super.onCreate(savedInstanceState)

        setContent {
            val appTheme by viewModel.theme.collectAsStateWithLifecycle()
            val useDynamicTheme by viewModel.useDynamicTheme.collectAsStateWithLifecycle()
            val darkTheme = shouldUseDarkTheme(theme = appTheme)

            val isLoggedIn by viewModel.isLoggedIn.collectAsStateWithLifecycle()
            val appState = rememberGoCartAppState()

            val statusBarStyle = statusBarStyle(appState, darkTheme)
            val navBarStyle = navBarStyle(appState, darkTheme)

            enableEdgeToEdge(
                statusBarStyle = statusBarStyle,
                navigationBarStyle = navBarStyle,
            )

            GoCartTheme(darkTheme = darkTheme, useDynamicTheme = useDynamicTheme) {
                GoCartApp(
                    startDestination = viewModel.startDestination.value,
                    appState = appState,
                    isLoggedIn = isLoggedIn,
                    onLogout = viewModel::logOut,
                )
            }
        }
    }

    @Composable
    private fun navBarStyle(appState: GoCartAppState, darkTheme: Boolean) =
        if (appState.isAuthenticationScreen) {
            SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT,
            ) { true }
        } else {
            SystemBarStyle.auto(
                lightScrim = lightScrim,
                darkScrim = darkScrim,
            ) { darkTheme }
        }

    @Composable
    private fun statusBarStyle(appState: GoCartAppState, darkTheme: Boolean) =
        if (appState.isAuthenticationScreen) {
            SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT,
            ) { true }
        } else {
            SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT,
            ) { darkTheme }
        }
}

@Composable
fun shouldUseDarkTheme(theme: AppTheme): Boolean {
    return when (theme) {
        AppTheme.LIGHT -> false
        AppTheme.DARK -> true
        AppTheme.SYSTEM -> isSystemInDarkTheme()
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
