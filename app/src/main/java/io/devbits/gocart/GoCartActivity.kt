package io.devbits.gocart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.currentBackStackEntryAsState
import io.devbits.gocart.authentication.navigation.navigateToAuth
import io.devbits.gocart.composeui.components.GoCartNavBar
import io.devbits.gocart.composeui.components.GoCartNavDrawerContent
import io.devbits.gocart.composeui.components.GoCartTopAppBar
import io.devbits.gocart.composeui.model.DestinationRoutes
import io.devbits.gocart.composeui.model.NavDrawerItem
import io.devbits.gocart.composeui.theme.GoCartTheme
import io.devbits.gocart.core.datastore.UserPreferences
import io.devbits.gocart.data.dataStore
import io.devbits.gocart.navigation.GoCartNavHost
import io.devbits.gocart.ui.GoCartAppState
import io.devbits.gocart.ui.rememberGoCartAppState
import kotlinx.coroutines.launch

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

            val statusBarStyle = if (appState.isAuthenticationScreen) {
                SystemBarStyle.auto(
                    lightScrim = android.graphics.Color.TRANSPARENT,
                    darkScrim = android.graphics.Color.TRANSPARENT,
                ) { true }
            } else {
                SystemBarStyle.auto(
                    lightScrim = android.graphics.Color.TRANSPARENT,
                    darkScrim = android.graphics.Color.TRANSPARENT,
                ) { false }
            }

            val navBarStyle = if (appState.isAuthenticationScreen) {
                SystemBarStyle.auto(
                    lightScrim = android.graphics.Color.TRANSPARENT,
                    darkScrim = android.graphics.Color.TRANSPARENT,
                ) { true }
            } else {
                SystemBarStyle.auto(
                    lightScrim = lightScrim,
                    darkScrim = lightScrim,
                ) { false }
            }

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
}


/**
 * The default light scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GoCartApp(
    startDestination: String,
    appState: GoCartAppState,
) {
    val isLoggedIn by appState.isLoggedIn.collectAsStateWithLifecycle()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    GoCartTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            // Hide during onboarding and authentication flows
            //  Only draw the content if the current route is a DestinationRoute.
            //  Disable gestures on non DestinationRoutes
            ModalNavigationDrawer(
                drawerContent = {
                    if (appState.currentDestinationRoute != null) {
                        GoCartNavDrawerContent(
                            isLoggedIn = isLoggedIn,
                            onClickHeader = { },
                            onSignUp = {
                                appState.scope.launch { drawerState.close() }
                                appState.navController.popBackStack()
                                appState.navController.navigateToAuth()
                            },
                            items = NavDrawerItem.values().asList(),
                            onClick = {
                                if (it == NavDrawerItem.LOGOUT) appState.logOut()
                                appState.scope.launch { drawerState.close() }
                                appState.navigateToRoute(it)
                            },
                        )
                    }
                },
                drawerState = drawerState,
                // Disable gestures in onboarding and authentication flows
                gesturesEnabled = appState.currentDestinationRoute != null,
            ) {
                Scaffold(
                    topBar = {
                        // Hide during onboarding and authentication flows
                        if (appState.currentDestinationRoute != null) {
                            GoCartTopAppBar(
                                onClickNavigation = {
                                    appState.scope.launch {
                                        drawerState.apply { if (isClosed) open() else close() }
                                    }
                                },
                                onSearch = {},
                                onCheckout = {},
                            )
                        }
                    },
                    bottomBar = {
                        // Hide during onboarding and authentication flows
                        if (appState.currentDestinationRoute != null) {
                            GoCartNavBar(
                                navigationRoutes = DestinationRoutes.values().asList(),
                                onNavigationSelected = appState::navigateToRoute,
                                currentDestination = appState.currentDestination,
                            )
                        }
                    },
                ) { innerPadding ->
                    // Fill the screen edge-to-edge in the AuthenticationScreen
                    val padding =
                        if (appState.isAuthenticationScreen) PaddingValues(0.dp) else innerPadding
                    GoCartNavHost(
                        modifier = Modifier
                            .padding(padding)
                            .consumeWindowInsets(innerPadding),
                        appState = appState,
                        startDestination = startDestination,
                    )
                }
            }
        }
    }
}
