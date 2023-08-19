package io.devbits.gocart.authentication.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.devbits.gocart.authentication.ui.AuthenticationRoute
import io.devbits.gocart.composeui.components.SystemBars

const val authenticationRoute = "authentication"

fun NavController.navigateToAuth(navOptions: NavOptions? = null) {
    this.navigate(authenticationRoute, navOptions)
}

fun NavGraphBuilder.authenticationScreen(
    modifier: Modifier = Modifier,
    onExploreApp: () -> Unit,
    onGoogleSignup: () -> Unit,
    onFacebookSignup: () -> Unit,
    onSignup: () -> Unit,
    onLogin: () -> Unit,
) {
    composable(
        route = authenticationRoute,
    ) {
        SystemBars(themed = false)
        AuthenticationRoute(
            modifier = modifier,
            onExploreApp = onExploreApp,
            onGoogleSignup = onGoogleSignup,
            onFacebookSignup = onFacebookSignup,
            onSignup = onSignup,
            onLogin = onLogin,
        )
    }
}