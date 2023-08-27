package io.devbits.gocart.homefeed.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.devbits.gocart.composeui.components.SystemBars
import io.devbits.gocart.composeui.model.DestinationRoutes
import io.devbits.gocart.composeui.model.NavDrawerItem
import io.devbits.gocart.core.data.UserPreferences
import io.devbits.gocart.homefeed.ui.HomeRoute

const val homeRoute = "home"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onClickHeader: () -> Unit,
    onSignUp: () -> Unit,
    onNavigationSelected: (DestinationRoutes) -> Unit,
    onDrawerItemClick: (NavDrawerItem) -> Unit,
    preferences: UserPreferences,
) {
    composable(route = homeRoute) {
        SystemBars(themed = true)
        HomeRoute(
            onClickHeader = onClickHeader,
            onSignUp = onSignUp,
            onNavigationSelected = onNavigationSelected,
            onDrawerItemClick = onDrawerItemClick,
            preferences = preferences,
        )
    }
}
