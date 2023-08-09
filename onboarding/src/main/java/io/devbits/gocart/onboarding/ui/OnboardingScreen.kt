package io.devbits.gocart.onboarding.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.devbits.gocart.composeui.theme.GoCartTheme
import io.devbits.gocart.onboarding.ui.components.OnboardingHorizontalPager

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onCompleteOnboarding: () -> Unit) {
    Surface {
        OnboardingHorizontalPager(onCompleteOnboarding = onCompleteOnboarding)
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    GoCartTheme {
        OnboardingScreen(onCompleteOnboarding = {})
    }
}