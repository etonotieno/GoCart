package io.devbits.gocart.navigation

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.devbits.gocart.composeui.components.SystemBars
import io.devbits.gocart.ui.address.AddressesScreen
import io.devbits.gocart.ui.favorites.FavoritesScreen
import io.devbits.gocart.ui.offers.OffersScreen
import io.devbits.gocart.ui.orders.OrdersScreen
import io.devbits.gocart.ui.payments.PaymentsScreen
import io.devbits.gocart.ui.services.ServicesScreen
import io.devbits.gocart.ui.settings.SettingsScreen

const val servicesRoute = "services"

fun NavController.navigateToServices(navOptions: NavOptions? = null) {
    this.navigate(servicesRoute, navOptions)
}

fun NavGraphBuilder.servicesScreen() {
    composable(route = servicesRoute) {
        SystemBars(themed = true)
        ServicesScreen(modifier = Modifier.safeDrawingPadding())
    }
}

const val ordersRoute = "orders"

fun NavController.navigateToOrders(navOptions: NavOptions? = null) {
    this.navigate(ordersRoute, navOptions)
}

fun NavGraphBuilder.ordersScreen() {
    composable(route = ordersRoute) {
        SystemBars(themed = true)
        OrdersScreen(modifier = Modifier.safeDrawingPadding())
    }
}

const val favoritesRoute = "favorites"

fun NavController.navigateToFavorites(navOptions: NavOptions? = null) {
    this.navigate(favoritesRoute, navOptions)
}

fun NavGraphBuilder.favoritesScreen() {
    composable(route = favoritesRoute) {
        SystemBars(themed = true)
        FavoritesScreen(modifier = Modifier.safeDrawingPadding())
    }
}

const val addressRoute = "address"

fun NavController.navigateToAddress(navOptions: NavOptions? = null) {
    this.navigate(addressRoute, navOptions)
}

fun NavGraphBuilder.addressScreen() {
    composable(route = addressRoute) {
        SystemBars(themed = true)
        AddressesScreen(modifier = Modifier.safeDrawingPadding())
    }
}

const val paymentsRoute = "payments"

fun NavController.navigateToPayments(navOptions: NavOptions? = null) {
    this.navigate(paymentsRoute, navOptions)
}

fun NavGraphBuilder.paymentsScreen() {
    composable(route = paymentsRoute) {
        SystemBars(themed = true)
        PaymentsScreen(modifier = Modifier.safeDrawingPadding())
    }
}

const val offersRoute = "offers"

fun NavController.navigateToOffers(navOptions: NavOptions? = null) {
    this.navigate(offersRoute, navOptions)
}

fun NavGraphBuilder.offersScreen() {
    composable(route = offersRoute) {
        SystemBars(themed = true)
        OffersScreen(modifier = Modifier.safeDrawingPadding())
    }
}

const val settingsRoute = "settings"

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(settingsRoute, navOptions)
}

fun NavGraphBuilder.settingsScreen() {
    composable(route = settingsRoute) {
        SystemBars(themed = true)
        SettingsScreen(modifier = Modifier.safeDrawingPadding())
    }
}
