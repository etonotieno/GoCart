package io.devbits.gocart.authentication.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.devbits.gocart.authentication.ui.signup.SignUpScreen

const val signUpRoute = "authentication/signup"

fun NavController.navigateToSignUp(navOptions: NavOptions? = null) {
    this.navigate(signUpRoute, navOptions)
}

fun NavGraphBuilder.signUpScreen(
    onSignup: () -> Unit,
    onLogin: () -> Unit,
    onBack: () -> Unit,
) {
    composable(route = signUpRoute) {
        SignUpScreen(
            onLogin = onLogin,
            onSignUp = onSignup,
            onBack = onBack,
        )
    }
}
