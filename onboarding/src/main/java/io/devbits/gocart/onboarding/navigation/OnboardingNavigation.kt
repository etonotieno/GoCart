package io.devbits.gocart.onboarding.navigation

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.devbits.gocart.onboarding.ui.OnboardingRoute

const val onboardingRoute = "onboarding"

fun NavGraphBuilder.onboardingScreen(onOnboarded: () -> Unit, modifier: Modifier = Modifier) {
    composable(route = onboardingRoute) {
        OnboardingRoute(modifier = modifier.safeDrawingPadding(), onOnboarded = onOnboarded)
    }
}