package io.devbits.gocart.onboarding.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.devbits.gocart.composeui.theme.GoCartTheme
import io.devbits.gocart.onboarding.R
import io.devbits.gocart.onboarding.ui.model.OnboardingItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingHorizontalPager(
    pages: List<OnboardingItem> = onboardingPages,
    pagerState: PagerState = rememberPagerState(pageCount = { pages.size }),
    onCompleteOnboarding: () -> Unit,
) {
    Box {
        HorizontalPager(
            state = pagerState,
        ) { currentPage ->
            OnboardingPage(page = pages[currentPage], Modifier.fillMaxSize())
        }

        OnboardingPagerIndicator(
            pagerState = pagerState,
            pages = pages,
            modifier = Modifier.align(Alignment.BottomCenter),
            onCompleteOnboarding = onCompleteOnboarding,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true)
@Composable
fun OnboardingHorizontalPagerPreview() {
    GoCartTheme {
        OnboardingHorizontalPager(onCompleteOnboarding = {})
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPagerIndicator(
    pages: List<OnboardingItem>,
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState(pageCount = { pages.size }),
    onCompleteOnboarding: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val onNext: () -> Unit = {
        coroutineScope.launch {
            val nextPage = pagerState.currentPage + 1
            if (pagerState.currentPage != pages.size - 1) {
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    val onBack: () -> Unit = {
        coroutineScope.launch {
            val previousPage = pagerState.currentPage - 1
            if (pagerState.currentPage != 0) {
                pagerState.animateScrollToPage(previousPage)
            }
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(8.dp))

        Button(onClick = onCompleteOnboarding) {
            Text(stringResource(R.string.text_button_skip_intro).uppercase())
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            TextButton(
                onClick = onBack,
                modifier = Modifier.alpha(if (pagerState.currentPage == 0) 0F else 1F)
            ) {
                Icon(painterResource(R.drawable.ic_left), null)
                Text(
                    text = stringResource(id = R.string.text_button_back).uppercase(),
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pages.size) { page ->
                    val color =
                        if (pagerState.currentPage == page) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurfaceVariant
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(8.dp)

                    )
                }
            }

            when (pagerState.currentPage) {
                0 -> {
                    TextButton(onClick = onNext) {
                        Text(
                            text = stringResource(id = R.string.text_button_start).uppercase(),
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                }
                // Final page
                pages.size - 1 -> {
                    TextButton(onClick = onCompleteOnboarding) {
                        Text(
                            text = stringResource(id = R.string.text_button_done).uppercase(),
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                }

                else -> {
                    TextButton(onClick = onNext) {
                        Text(
                            text = stringResource(id = R.string.text_button_next).uppercase(),
                            style = MaterialTheme.typography.labelLarge,
                        )
                        Icon(painterResource(R.drawable.ic_right), null)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun OnboardingPagerIndicatorPreview() {
    GoCartTheme {
        OnboardingPagerIndicator(
            pages = onboardingPages,
            onCompleteOnboarding = {}
        )
    }
}

val onboardingPages
    @Composable
    get() = listOf(
        OnboardingItem(
            R.drawable.ic_fresh_produce,
            stringResource(R.string.text_onboarding_fresh_produce_title),
            stringResource(R.string.text_onboarding_fresh_produce_description)
        ),
        OnboardingItem(
            R.drawable.ic_fast_delivery,
            stringResource(R.string.text_onboarding_fast_delivery_title),
            stringResource(R.string.text_onboarding_fast_delivery_description)
        ),
        OnboardingItem(
            R.drawable.ic_easy_payments,
            stringResource(R.string.text_onboarding_easy_payments_title),
            stringResource(R.string.text_onboarding_easy_payments_description)
        ),
    )
