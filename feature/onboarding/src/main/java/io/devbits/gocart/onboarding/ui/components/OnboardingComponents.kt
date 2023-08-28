package io.devbits.gocart.onboarding.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
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
            modifier = Modifier.size(280.dp)
        )

        Spacer(modifier = Modifier.size(24.dp))

        Text(
            text = page.title,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.size(24.dp))

        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(32.dp)
        )
    }
}

@Preview(
    showBackground = true, backgroundColor = 0xFFFFFFFF,
    group = "Onboarding", name = "Page One"
)
@Composable
fun OnboardingPageOnePreview() {
    GoCartTheme {
        OnboardingPage(OnboardingPageOne)
    }
}

@Preview(
    showBackground = true, backgroundColor = 0xFFFFFFFF,
    group = "Onboarding", name = "Page Two"
)
@Composable
fun OnboardingPageTwoPreview() {
    GoCartTheme {
        OnboardingPage(OnboardingPageTwo)
    }
}

@Preview(
    showBackground = true, backgroundColor = 0xFFFFFFFF,
    name = "Page Three", group = "Onboarding"
)
@Composable
fun OnboardingPageThreePreview() {
    GoCartTheme {
        OnboardingPage(OnboardingPageThree)
    }
}
