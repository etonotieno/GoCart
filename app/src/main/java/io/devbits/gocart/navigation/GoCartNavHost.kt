package io.devbits.gocart.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import io.devbit.gocart.orders.navigation.ordersScreen
import io.devbits.gocart.address.navigation.addressScreen
import io.devbits.gocart.authentication.navigation.authHomeScreen
import io.devbits.gocart.authentication.navigation.loginScreen
import io.devbits.gocart.authentication.navigation.navigateToAuth
import io.devbits.gocart.authentication.navigation.navigateToLogin
import io.devbits.gocart.authentication.navigation.navigateToSignUp
import io.devbits.gocart.authentication.navigation.signUpScreen
import io.devbits.gocart.favorites.navigation.favoritesScreen
import io.devbits.gocart.homefeed.navigation.homeScreen
import io.devbits.gocart.homefeed.navigation.navigateToHome
import io.devbits.gocart.offers.navigation.offersScreen
import io.devbits.gocart.onboarding.navigation.onboardingScreen
import io.devbits.gocart.payments.navigation.paymentsScreen
import io.devbits.gocart.services.navigation.servicesScreen
import io.devbits.gocart.settings.navigation.settingsScreen
import io.devbits.gocart.ui.GoCartAppState
import kotlinx.coroutines.launch

@Composable
fun GoCartNavHost(
    appState: GoCartAppState,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = appState.navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        onboardingScreen(
            onOnboarded = {
                // TODO: Move authentication logic to a ViewModel
                appState.scope.launch { appState.preferences.setOnboarded() }
                appState.navController.popBackStack()
                appState.navController.navigateToAuth()
            }
        )

        authHomeScreen(
            onExploreApp = {
                // TODO: Move authentication logic to a ViewModel
                appState.scope.launch { appState.preferences.setGuestUser(true) }
                appState.navController.popBackStack()
                appState.navController.navigateToHome()
            },
            onGoogleSignup = appState.navController::navigateToSignUp,
            onFacebookSignup = appState.navController::navigateToSignUp,
            onSignup = appState.navController::navigateToSignUp,
            onLogin = appState.navController::navigateToLogin,
        )

        signUpScreen(
            onSignup = {
                // TODO: Move authentication logic to a ViewModel
                appState.scope.launch { appState.preferences.setAuthenticated(true) }
                appState.navController.popBackStack()
                appState.navController.navigateToHome()
            },
            onLogin = appState.navController::navigateToLogin,
            onBack = appState.navController::popBackStack
        )

        loginScreen(
            onBack = appState.navController::popBackStack,
            onLogin = {
                // TODO: Move authentication logic to a ViewModel
                appState.scope.launch { appState.preferences.setAuthenticated(true) }
                appState.navController.popBackStack()
                appState.navController.navigateToHome()
            },
            navigateToSignUp = appState.navController::navigateToSignUp,
            onForgotPassword = {},
        )

        homeScreen()

        servicesScreen()

        ordersScreen()

        favoritesScreen()

        addressScreen()

        paymentsScreen()

        offersScreen()

        settingsScreen()
    }
}
