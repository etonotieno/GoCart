package io.devbits.gocart.onboarding.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.devbits.gocart.composeui.theme.GoCartTheme
import io.devbits.gocart.onboarding.ui.components.OnboardingHorizontalPager

@Composable
fun OnboardingRoute(onOnboarded: () -> Unit, modifier: Modifier) {
    OnboardingScreen(modifier = modifier, onOnboarded = onOnboarded)
}

@Composable
fun OnboardingScreen(onOnboarded: () -> Unit, modifier: Modifier = Modifier) {
    OnboardingHorizontalPager(modifier = modifier, onOnboarded = onOnboarded)
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun OnboardingScreenPreview() {
    GoCartTheme {
        OnboardingScreen(onOnboarded = {})
    }
}
