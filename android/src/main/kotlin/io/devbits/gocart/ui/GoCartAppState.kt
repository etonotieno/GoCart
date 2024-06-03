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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import io.devbit.gocart.orders.navigation.navigateToOrders
import io.devbit.gocart.orders.navigation.ordersRoute
import io.devbits.gocart.address.navigation.navigateToAddress
import io.devbits.gocart.authentication.navigation.authenticationRoute
import io.devbits.gocart.authentication.navigation.navigateToAuth
import io.devbits.gocart.cart.navigation.navigateToCart
import io.devbits.gocart.designsystem.model.DestinationRoutes
import io.devbits.gocart.designsystem.model.NavDrawerItem
import io.devbits.gocart.favorites.navigation.favoritesRoute
import io.devbits.gocart.favorites.navigation.navigateToFavorites
import io.devbits.gocart.homefeed.navigation.homeRoute
import io.devbits.gocart.homefeed.navigation.navigateToHome
import io.devbits.gocart.offers.navigation.navigateToOffers
import io.devbits.gocart.payments.navigation.navigateToPayments
import io.devbits.gocart.services.navigation.navigateToServices
import io.devbits.gocart.services.navigation.servicesRoute
import io.devbits.gocart.settings.navigation.navigateToSettings
import kotlinx.coroutines.CoroutineScope

@Stable
class GoCartAppState(
    val navController: NavHostController,
    val scope: CoroutineScope,
) {

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentDestinationRoute: DestinationRoutes?
        @Composable get() {
            return when (currentDestination?.route) {
                homeRoute -> DestinationRoutes.HOME
                servicesRoute -> DestinationRoutes.SERVICES
                ordersRoute -> DestinationRoutes.ORDERS
                favoritesRoute -> DestinationRoutes.FAVORITES
                else -> null
            }
        }

    val isAuthenticationScreen
        @Composable get() = currentDestination?.route == authenticationRoute

    fun navigateToRoute(route: DestinationRoutes) {
        val navOptions = getRouteNavOptions()
        when (route) {
            DestinationRoutes.HOME -> navController.navigateToHome(navOptions)
            DestinationRoutes.SERVICES -> navController.navigateToServices(navOptions)
            DestinationRoutes.ORDERS -> navController.navigateToOrders(navOptions)
            DestinationRoutes.FAVORITES -> navController.navigateToFavorites(navOptions)
        }
    }

    // Represent Drawer Items as top level navigation routes
    fun navigateToRoute(route: NavDrawerItem) {
        when (route) {
            NavDrawerItem.ADDRESS -> navController.navigateToAddress()
            NavDrawerItem.PAYMENTS -> navController.navigateToPayments()
            NavDrawerItem.OFFERS -> navController.navigateToOffers()
            NavDrawerItem.SETTINGS -> navController.navigateToSettings()
            NavDrawerItem.HELP -> {}
            NavDrawerItem.LOGOUT -> {
                navController.popBackStack()
                navController.navigateToAuth()
            }
        }
    }

    private fun getRouteNavOptions(): NavOptions {
        return navOptions {
            // Pop up to the start destination
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Only one copy of the Route is saved on the backstack during re-selection
            launchSingleTop = true
            // Restore state when re-selecting a previously selected item
            restoreState = true
        }
    }

    fun navigateToCart() = navController.navigateToCart()
}

@Composable
fun rememberGoCartAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): GoCartAppState {
    return remember(navController, coroutineScope) {
        GoCartAppState(
            navController = navController,
            scope = coroutineScope,
        )
    }
}
