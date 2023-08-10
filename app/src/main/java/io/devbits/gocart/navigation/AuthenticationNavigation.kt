package io.devbits.gocart.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.devbits.gocart.ui.auth.AuthenticationScreen

const val authenticationRoute = "authentication"

fun NavController.navigateToAuth(navOptions: NavOptions? = null) {
    this.navigate(authenticationRoute, navOptions)
}

fun NavGraphBuilder.authenticationScreen(onExploreApp: () -> Unit) {
    composable(
        route = authenticationRoute,
    ) {
        AuthenticationScreen(onExploreApp = onExploreApp)
    }
}