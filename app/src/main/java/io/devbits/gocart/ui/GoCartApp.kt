package io.devbits.gocart.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.devbits.gocart.authentication.navigation.navigateToAuth
import io.devbits.gocart.composeui.components.GoCartNavBar
import io.devbits.gocart.composeui.components.GoCartNavDrawerContent
import io.devbits.gocart.composeui.components.GoCartTopAppBar
import io.devbits.gocart.composeui.model.DestinationRoutes
import io.devbits.gocart.composeui.model.NavDrawerItem
import io.devbits.gocart.composeui.theme.GoCartTheme
import io.devbits.gocart.navigation.GoCartNavHost
import kotlinx.coroutines.launch

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
