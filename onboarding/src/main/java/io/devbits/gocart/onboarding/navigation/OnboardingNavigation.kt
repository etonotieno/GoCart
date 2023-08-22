package io.devbits.gocart.onboarding.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.devbits.gocart.composeui.components.SystemBars
import io.devbits.gocart.onboarding.ui.OnboardingRoute

const val onboardingRoute = "onboarding"

fun NavGraphBuilder.onboardingScreen(onOnboarded: () -> Unit, modifier: Modifier = Modifier) {
    composable(route = onboardingRoute) {
        SystemBars(themed = true)
        OnboardingRoute(modifier = modifier, onOnboarded = onOnboarded)
    }
}
