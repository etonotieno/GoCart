package io.devbits.gocart.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.devbits.gocart.authentication.navigation.authenticationScreen
import io.devbits.gocart.authentication.navigation.navigateToAuth
import io.devbits.gocart.core.data.UserPreferences
import io.devbits.gocart.onboarding.navigation.onboardingScreen
import kotlinx.coroutines.launch

@Composable
fun GoCartNavHost(
    navController: NavHostController,
    startDestination: String,
    preferences: UserPreferences,
    isLoggedIn: Boolean,
) {
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = startDestination) {
        onboardingScreen(
            onOnboarded = {
                scope.launch {
                    preferences.setOnboarded()
                    navController.popBackStack()
                    navController.navigateToAuth()
                }
            }
        )

        authenticationScreen(
            onExploreApp = {
                scope.launch {
                    preferences.setIsGuestUser()
                    navController.popBackStack()
                    navController.navigateToHome()
                }
            },
            onGoogleSignup = {
                scope.launch {
                    preferences.setAuthenticated(true)
                    navController.popBackStack()
                    navController.navigateToHome()
                }
            },
            onFacebookSignup = {
                scope.launch {
                    preferences.setAuthenticated(true)
                    navController.popBackStack()
                    navController.navigateToHome()
                }
            },
            onSignup = {
                scope.launch {
                    preferences.setAuthenticated(true)
                    navController.popBackStack()
                    navController.navigateToHome()
                }
            },
            onLogin = {
                scope.launch {
                    preferences.setAuthenticated(true)
                    navController.popBackStack()
                    navController.navigateToHome()
                }
            },
        )

        homeScreen(
            isLoggedIn = isLoggedIn,
            onProfileClick = {},
            onSignUp = {
                navController.popBackStack()
                navController.navigateToAuth()
            },
            onLogout = {
                scope.launch {
                    preferences.setAuthenticated(false)
                    navController.popBackStack()
                    navController.navigateToAuth()
                }
            },
            onClickMyAddresses = {},
            onClickPayments = {},
            onClickSpecialOffers = {},
            onClickSettings = {},
            onClickHelp = {},
        )
    }
}