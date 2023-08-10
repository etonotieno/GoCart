package io.devbits.gocart.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.devbits.gocart.onboarding.ui.OnboardingScreen

const val onboardingRoute = "onboarding"

fun NavGraphBuilder.onboardingScreen(onCompleteOnboarding: () -> Unit) {
    composable(route = onboardingRoute) {
        OnboardingScreen(onCompleteOnboarding = onCompleteOnboarding)
    }
}