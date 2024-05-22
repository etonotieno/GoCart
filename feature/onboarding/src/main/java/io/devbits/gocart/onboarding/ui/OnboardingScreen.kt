/*
 * Copyright 2023 Eton Otieno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.devbits.gocart.onboarding.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import io.devbits.gocart.designsystem.theme.GoCartSurface
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.onboarding.ui.components.OnboardingHorizontalPager

@Composable
fun OnboardingRoute(
    onOnboarded: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    OnboardingScreen(
        modifier = modifier,
        onOnboarded = {
            viewModel.setOnboarded()
            onOnboarded()
        },
    )
}

@Composable
fun OnboardingScreen(onOnboarded: () -> Unit, modifier: Modifier = Modifier) {
    GoCartSurface(modifier = modifier) {
        OnboardingHorizontalPager(onOnboarded = onOnboarded)
    }
}

@Preview
@Composable
private fun OnboardingScreenPreview() {
    GoCartTheme {
        OnboardingScreen(onOnboarded = {})
    }
}
