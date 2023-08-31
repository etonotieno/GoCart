package io.devbits.gocart.offers.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.devbits.gocart.offers.ui.OffersScreen

const val offersRoute = "offers"

fun NavController.navigateToOffers(navOptions: NavOptions? = null) {
    this.navigate(offersRoute, navOptions)
}


fun NavGraphBuilder.offersScreen() {
    composable(route = offersRoute) {
        OffersScreen()
    }
}
