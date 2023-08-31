package io.devbits.gocart.onboarding.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.onboarding.ui.component.OnboardingHorizontalPager

@Composable
fun OnboardingScreen(
    onOnboarded: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    OnboardingScreen(modifier = modifier, onOnboarded = onOnboarded, state = state)
}

@Composable
fun OnboardingScreen(
    onOnboarded: () -> Unit,
    modifier: Modifier = Modifier,
    state: String,
) {
    OnboardingHorizontalPager(modifier = modifier, onOnboarded = onOnboarded)
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun OnboardingScreenPreview() {
    GoCartTheme {
        OnboardingScreen(onOnboarded = {}, state = "")
    }
}
