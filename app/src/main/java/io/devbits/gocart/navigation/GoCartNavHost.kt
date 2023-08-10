package io.devbits.gocart.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.devbits.gocart.onboarding.navigation.onboardingRoute
import io.devbits.gocart.onboarding.navigation.onboardingScreen

@Composable
fun GoCartNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = onboardingRoute) {
        onboardingScreen(onCompleteOnboarding = navController::navigateToAuth)
        authenticationScreen(onExploreApp = navController::navigateToHome)
        homeScreen()
    }
}