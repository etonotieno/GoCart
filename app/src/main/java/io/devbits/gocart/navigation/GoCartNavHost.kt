package io.devbits.gocart.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import io.devbits.gocart.authentication.navigation.authHomeScreen
import io.devbits.gocart.authentication.navigation.loginScreen
import io.devbits.gocart.authentication.navigation.navigateToAuth
import io.devbits.gocart.authentication.navigation.navigateToLogin
import io.devbits.gocart.authentication.navigation.navigateToSignUp
import io.devbits.gocart.authentication.navigation.signUpScreen
import io.devbits.gocart.composeui.model.DestinationRoutes
import io.devbits.gocart.composeui.model.NavDrawerItem
import io.devbits.gocart.core.data.UserPreferences
import io.devbits.gocart.onboarding.navigation.onboardingScreen
import kotlinx.coroutines.launch

@Composable
fun GoCartNavHost(
    navController: NavHostController,
    startDestination: String,
    preferences: UserPreferences,
) {
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = startDestination) {
        onboardingScreen(
            onOnboarded = {
                // TODO: Move authentication logic to a ViewModel
                scope.launch { preferences.setOnboarded() }
                navController.popBackStack()
                navController.navigateToAuth()
            }
        )

        authHomeScreen(
            onExploreApp = {
                // TODO: Move authentication logic to a ViewModel
                scope.launch { preferences.setIsGuestUser() }
                navController.popBackStack()
                navController.navigateToHome(
                    navOptions = navOptions {
                        launchSingleTop = true
                    }
                )
            },
            onGoogleSignup = navController::navigateToSignUp,
            onFacebookSignup = navController::navigateToSignUp,
            onSignup = navController::navigateToSignUp,
            onLogin = navController::navigateToLogin,
        )

        signUpScreen(
            onSignup = {
                // TODO: Move authentication logic to a ViewModel
                scope.launch { preferences.setAuthenticated(true) }
                navController.popBackStack()
                navController.navigateToHome()
            },
            onLogin = navController::navigateToLogin,
            onBack = navController::popBackStack
        )

        loginScreen(
            onBack = navController::popBackStack,
            onLogin = {
                // TODO: Move authentication logic to a ViewModel
                scope.launch { preferences.setAuthenticated(true) }
                navController.popBackStack()
                navController.navigateToHome()
            },
            navigateToSignUp = navController::navigateToSignUp,
            onForgotPassword = {},
        )

        homeScreen(
            onClickHeader = {},
            onSignUp = {
                navController.popBackStack()
                navController.navigateToAuth()
            },
            onNavigationSelected = {
                when (it) {
                    DestinationRoutes.HOME -> navController.navigateToHome()
                    DestinationRoutes.SERVICES -> navController.navigateToServices()
                    DestinationRoutes.ORDERS -> navController.navigateToOrders()
                    DestinationRoutes.FAVORITES -> navController.navigateToFavorites()
                }
            },
            onDrawerItemClick = {
                when (it) {
                    NavDrawerItem.ADDRESS -> navController.navigateToAddress()
                    NavDrawerItem.PAYMENTS -> navController.navigateToPayments()
                    NavDrawerItem.OFFERS -> navController.navigateToOffers()
                    NavDrawerItem.SETTINGS -> navController.navigateToSettings()
                    NavDrawerItem.HELP -> {}
                    NavDrawerItem.LOGOUT -> {
                        // TODO: Move authentication logic to a ViewModel
                        scope.launch { preferences.setAuthenticated(false) }
                        navController.popBackStack()
                        navController.navigateToAuth()
                    }
                }
            },
            preferences = preferences,
        )

        servicesScreen()

        ordersScreen()

        favoritesScreen()

        addressScreen()

        paymentsScreen()

        offersScreen()

        settingsScreen()
    }
}
