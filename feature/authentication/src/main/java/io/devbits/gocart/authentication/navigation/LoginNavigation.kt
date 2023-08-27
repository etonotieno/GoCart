package io.devbits.gocart.authentication.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.devbits.gocart.authentication.ui.login.LoginScreen

const val loginRoute = "authentication/login"

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    this.navigate(loginRoute, navOptions)
}

fun NavGraphBuilder.loginScreen(
    onBack: () -> Unit,
    onLogin: () -> Unit,
    onForgotPassword: () -> Unit,
    navigateToSignUp: () -> Unit,
) {
    composable(route = loginRoute) {
        LoginScreen(
            onBack = onBack,
            onLogin = onLogin,
            navigateToSignUp = navigateToSignUp,
            onForgotPassword = onForgotPassword,
        )
    }
}
