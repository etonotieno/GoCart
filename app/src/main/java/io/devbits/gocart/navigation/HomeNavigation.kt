package io.devbits.gocart.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.devbits.gocart.composeui.components.SystemBars
import io.devbits.gocart.ui.home.HomeRoute

const val homeRoute = "home"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(
    modifier: Modifier = Modifier,
    isLoggedIn: Boolean,
    onProfileClick: () -> Unit,
    onSignUp: () -> Unit,
    onLogout: () -> Unit,
    onClickMyAddresses: () -> Unit,
    onClickPayments: () -> Unit,
    onClickSpecialOffers: () -> Unit,
    onClickSettings: () -> Unit,
    onClickHelp: () -> Unit,
) {
    composable(route = homeRoute) {
        SystemBars(themed = true)
        HomeRoute(
            modifier = modifier,
            isLoggedIn = isLoggedIn,
            onProfileClick = onProfileClick,
            onSignUp = onSignUp,
            onLogout = onLogout,
            onClickMyAddresses = onClickMyAddresses,
            onClickPayments = onClickPayments,
            onClickSpecialOffers = onClickSpecialOffers,
            onClickSettings = onClickSettings,
            onClickHelp = onClickHelp,
        )
    }
}