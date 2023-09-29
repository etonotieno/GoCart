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
package io.devbits.gocart.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.devbits.gocart.authentication.navigation.navigateToAuth
import io.devbits.gocart.designsystem.component.GoCartNavBar
import io.devbits.gocart.designsystem.component.GoCartNavDrawerContent
import io.devbits.gocart.designsystem.component.GoCartTopAppBar
import io.devbits.gocart.designsystem.model.DestinationRoutes
import io.devbits.gocart.designsystem.model.NavDrawerItem
import io.devbits.gocart.navigation.GoCartNavHost
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GoCartApp(
    startDestination: String,
    appState: GoCartAppState,
    isLoggedIn: Boolean,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        // Hide during onboarding and authentication flows
        //  Only draw the content if the current route is a DestinationRoute.
        //  Disable gestures on non DestinationRoutes
        ModalNavigationDrawer(
            drawerContent = {
                if (appState.currentDestinationRoute != null) {
                    ModalDrawerSheet {
                        GoCartNavDrawerContent(
                            isLoggedIn = isLoggedIn,
                            onClickHeader = { },
                            onSignUp = {
                                appState.scope.launch { drawerState.close() }
                                appState.navController.popBackStack()
                                appState.navController.navigateToAuth()
                            },
                            items = NavDrawerItem.entries,
                            onClick = {
                                if (it == NavDrawerItem.LOGOUT) onLogout()
                                appState.scope.launch { drawerState.close() }
                                appState.navigateToRoute(it)
                            },
                        )
                    }
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
                            onCheckout = appState::navigateToCart,
                        )
                    }
                },
                bottomBar = {
                    // Hide during onboarding and authentication flows
                    if (appState.currentDestinationRoute != null) {
                        GoCartNavBar(
                            navigationRoutes = DestinationRoutes.entries,
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
                    appState = appState,
                    startDestination = startDestination,
                    modifier = Modifier
                        .padding(padding)
                        .consumeWindowInsets(innerPadding),
                )
            }
        }
    }
}
