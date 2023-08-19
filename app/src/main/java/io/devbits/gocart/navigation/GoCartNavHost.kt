package io.devbits.gocart.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.devbits.gocart.authentication.navigation.authenticationScreen
import io.devbits.gocart.authentication.navigation.navigateToAuth
import io.devbits.gocart.core.data.UserPreferences
import io.devbits.gocart.onboarding.navigation.onboardingScreen
import io.devbits.gocart.ui.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun GoCartNavHost(
    viewModel: MainViewModel,
    preferences: UserPreferences,
) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = viewModel.startDestination.value) {
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
                    preferences.setAuthenticated()
                    navController.popBackStack()
                    navController.navigateToHome()
                }
            },
            onGoogleSignup = {},
            onFacebookSignup = {},
            onSignup = {},
            onLogin = {},
        )

        homeScreen()
    }
}