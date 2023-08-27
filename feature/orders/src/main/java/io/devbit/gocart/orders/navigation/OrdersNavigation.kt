package io.devbit.gocart.orders.navigation

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.devbit.gocart.orders.ui.OrdersScreen
import io.devbits.gocart.composeui.components.SystemBars

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
