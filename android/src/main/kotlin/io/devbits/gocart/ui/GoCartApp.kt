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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.devbit.gocart.orders.navigation.ordersRoute
import io.devbits.gocart.authentication.navigation.navigateToAuth
import io.devbits.gocart.designsystem.component.GCTopAppBar
import io.devbits.gocart.designsystem.component.GoCartNavBar
import io.devbits.gocart.designsystem.component.GoCartNavDrawerContent
import io.devbits.gocart.designsystem.model.DestinationRoutes
import io.devbits.gocart.designsystem.model.NavDrawerItem
import io.devbits.gocart.designsystem.theme.GoCartSurface
import io.devbits.gocart.favorites.navigation.favoritesRoute
import io.devbits.gocart.homefeed.navigation.homeRoute
import io.devbits.gocart.navigation.GoCartNavHost
import io.devbits.gocart.resources.R
import io.devbits.gocart.services.navigation.servicesRoute
import kotlinx.coroutines.launch

@Composable
fun GoCartApp(
    startDestination: String,
    appState: GoCartAppState,
    isLoggedIn: Boolean,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val currentDestinationRoute = appState.currentDestination?.route

    GoCartSurface(modifier = modifier.fillMaxSize()) {
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
                        GoCartMainTopAppBar(
                            currentDestinationRoute = currentDestinationRoute,
                            appState = appState,
                            drawerState = drawerState
                        )
                    }
                },
                bottomBar = {
                    // Hide during onboarding and authentication flows
                    if (appState.currentDestinationRoute != null) {
                        GoCartNavBar(
                            navigationRoutes = DestinationRoutes.entries,
                            onClickItem = appState::navigateToRoute,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GoCartMainTopAppBar(
    currentDestinationRoute: String?,
    appState: GoCartAppState,
    drawerState: DrawerState,
) {
    GCTopAppBar(
        title = {
            when (currentDestinationRoute) {
                homeRoute -> {
                    Image(
                        painter = painterResource(id = R.drawable.ic_go_cart_horizontal_color),
                        contentDescription = null,
                        modifier = Modifier.height(24.dp),
                    )
                }

                servicesRoute -> {
                    Text(text = "Services")
                }

                ordersRoute -> {
                    Text(text = "Orders")
                }

                favoritesRoute -> {
                    Text(text = "Favorites")
                }

                else -> Unit
            }
        },
        actions = {
            if (currentDestinationRoute == homeRoute ||
                currentDestinationRoute == favoritesRoute
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                    )
                }

                IconButton(
                    onClick = {
                        appState.navigateToCart()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingCart,
                        contentDescription = null,
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    appState.scope.launch {
                        drawerState.apply { if (isClosed) open() else close() }
                    }
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null,
                )
            }
        },
    )
}
