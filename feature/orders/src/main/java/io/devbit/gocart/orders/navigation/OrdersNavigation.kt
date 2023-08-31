package io.devbit.gocart.orders.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.devbit.gocart.orders.ui.OrdersScreen

const val ordersRoute = "orders"

fun NavController.navigateToOrders(navOptions: NavOptions? = null) {
    this.navigate(ordersRoute, navOptions)
}

fun NavGraphBuilder.ordersScreen() {
    composable(route = ordersRoute) {
        OrdersScreen()
    }
}
