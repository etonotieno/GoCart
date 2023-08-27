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
import io.devbits.gocart.composeui.model.DestinationRoutes
import io.devbits.gocart.composeui.model.NavDrawerItem
import io.devbits.gocart.core.datastore.UserPreferences
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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@Stable
class GoCartAppState(
    val navController: NavHostController,
    val preferences: UserPreferences,
    val scope: CoroutineScope,
) {

    val isLoggedIn: StateFlow<Boolean>
        get() = preferences.isAuthenticated()
            .stateIn(
                scope = scope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = false,
            )

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

    // TODO: Represent Drawer Items as top level navigation routes
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
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }

    fun logOut() {
        // TODO: Move authentication logic to a ViewModel
        scope.launch {
            preferences.setGuestUser(false)
            preferences.setAuthenticated(false)
        }
    }
}

@Composable
fun rememberGoCartAppState(
    preferences: UserPreferences,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): GoCartAppState {
    return remember(
        navController,
        preferences,
        coroutineScope,
    ) {
        GoCartAppState(
            navController = navController,
            preferences = preferences,
            scope = coroutineScope,
        )
    }
}
