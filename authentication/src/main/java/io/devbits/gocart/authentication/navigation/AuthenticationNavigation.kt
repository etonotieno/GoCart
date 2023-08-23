package io.devbits.gocart.authentication.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.devbits.gocart.authentication.ui.AuthenticationScreen
import io.devbits.gocart.composeui.components.SystemBars

const val authenticationRoute = "authentication"

fun NavController.navigateToAuth(navOptions: NavOptions? = null) {
    this.navigate(authenticationRoute, navOptions)
}

fun NavController.navigateToAuthGraph(navOptions: NavOptions? = null) {
    this.navigate("authentication_graph", navOptions)
}

fun NavGraphBuilder.authHomeScreen(
    onExploreApp: () -> Unit,
    onGoogleSignup: () -> Unit,
    onFacebookSignup: () -> Unit,
    onSignup: () -> Unit,
    onLogin: () -> Unit,
) {
    composable(route = authenticationRoute) {
        SystemBars(themed = false)
        AuthenticationScreen(
            onExploreApp = onExploreApp,
            onGoogleSignup = onGoogleSignup,
            onFacebookSignup = onFacebookSignup,
            onSignup = onSignup,
            onLogin = onLogin,
        )
    }
}

fun NavGraphBuilder.authGraph(
    onExploreApp: () -> Unit,
    onGoogleSignup: () -> Unit,
    onFacebookSignup: () -> Unit,
    onSignup: () -> Unit,
    onLogin: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        startDestination = authenticationRoute,
        route = "authentication_graph"
    ) {
        authHomeScreen(
            onExploreApp = onExploreApp,
            onGoogleSignup = onGoogleSignup,
            onFacebookSignup = onFacebookSignup,
            onSignup = onSignup,
            onLogin = onLogin,
        )
        nestedGraphs()
    }
}
