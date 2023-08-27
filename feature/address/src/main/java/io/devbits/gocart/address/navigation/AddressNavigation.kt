package io.devbits.gocart.address.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.devbits.gocart.address.ui.AddressesScreen

const val addressRoute = "address"

fun NavController.navigateToAddress(navOptions: NavOptions? = null) {
    this.navigate(addressRoute, navOptions)
}

fun NavGraphBuilder.addressScreen() {
    composable(route = addressRoute) {
        AddressesScreen(modifier = Modifier)
    }
}
