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
package io.devbits.gocart.onboarding.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import io.devbits.gocart.designsystem.theme.GoCartSurface
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.onboarding.R
import io.devbits.gocart.onboarding.ui.model.OnboardingItem

@Composable
fun OnboardingPage(page: OnboardingItem, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(page.imageRes),
            contentDescription = stringResource(R.string.onboarding_image_cd),
            modifier = Modifier.size(280.dp),
        )

        Spacer(modifier = Modifier.size(24.dp))

        Text(
            text = stringResource(page.title),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.size(24.dp))

        Text(
            text = stringResource(page.description),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(32.dp),
        )
    }
}

class OnboardingPagesPreviewProvider(
    override val values: Sequence<OnboardingItem> = onboardingPages.asSequence(),
) : PreviewParameterProvider<OnboardingItem>

@Preview
@Composable
private fun OnboardingPagesPreview(
    @PreviewParameter(OnboardingPagesPreviewProvider::class)
    page: OnboardingItem,
) {
    GoCartTheme {
        GoCartSurface {
            OnboardingPage(page = page, modifier = Modifier.fillMaxSize())
        }
    }
}
