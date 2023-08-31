package io.devbits.gocart.payments.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.devbits.gocart.payments.ui.PaymentsScreen

const val paymentsRoute = "payments"

fun NavController.navigateToPayments(navOptions: NavOptions? = null) {
    this.navigate(paymentsRoute, navOptions)
}

fun NavGraphBuilder.paymentsScreen() {
    composable(route = paymentsRoute) {
        PaymentsScreen()
    }
}
