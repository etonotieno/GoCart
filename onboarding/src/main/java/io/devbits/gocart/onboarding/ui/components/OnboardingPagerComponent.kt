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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun OnboardingHorizontalPager(onOnboarded: () -> Unit, modifier: Modifier = Modifier) {
    val pages = onboardingPages
    val pagerState = rememberPagerState(pageCount = { pages.size })
    Box(modifier = modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState) { currentPage ->
            OnboardingPage(page = pages[currentPage], Modifier.fillMaxSize())
        }

        OnboardingPagerIndicator(
            pagerState = pagerState,
            pages = pages,
            modifier = Modifier.align(Alignment.BottomCenter),
            onOnboarded = onOnboarded,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true)
@Composable
fun OnboardingHorizontalPagerPreview() {
    GoCartTheme {
        OnboardingHorizontalPager(onOnboarded = {})
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPagerIndicator(
    pages: List<OnboardingItem>,
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onOnboarded: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val start = stringResource(R.string.text_button_start)
    val back = stringResource(R.string.text_button_back)
    val next = stringResource(R.string.text_button_next)
    val done = stringResource(R.string.text_button_done)

    val buttonsState by remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                0 -> OnboardingButtonState() to OnboardingButtonState(text = start)
                1 -> OnboardingButtonState(
                    text = back,
                    startIcon = R.drawable.ic_left
                ) to OnboardingButtonState(
                    text = next,
                    endIcon = R.drawable.ic_right
                )

                2 -> OnboardingButtonState(
                    text = back,
                    startIcon = R.drawable.ic_left
                ) to OnboardingButtonState(text = done)

                else -> OnboardingButtonState() to OnboardingButtonState()
            }
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(8.dp))

        Button(onClick = onOnboarded) {
            Text(stringResource(R.string.text_button_skip_intro).uppercase())
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            OnboardingButton(
                state = buttonsState.first,
                onClick = {
                    coroutineScope.launch {
                        val previousPage = pagerState.currentPage - 1
                        if (pagerState.currentPage != 0) {
                            pagerState.animateScrollToPage(previousPage)
                        }
                    }
                },
            )

            Indicator(
                pageSize = pages.size,
                currentPage = pagerState.currentPage,
            )

            OnboardingButton(
                state = buttonsState.second,
                onClick = {
                    coroutineScope.launch {
                        if (pagerState.currentPage == 2) {
                            onOnboarded()
                        } else {
                            val nextPage = pagerState.currentPage + 1
                            if (pagerState.currentPage != pages.size - 1) {
                                pagerState.animateScrollToPage(nextPage)
                            }
                        }
                    }
                },
            )
        }
    }
}

@Composable
private fun Indicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    currentPage: Int,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(pageSize) { page ->
            val color =
                if (currentPage == page) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurfaceVariant
            Box(
                modifier = modifier
                    .padding(2.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color)
            )
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
        val pages = onboardingPages
        OnboardingPagerIndicator(
            pages = pages,
            pagerState = rememberPagerState { pages.size },
            onOnboarded = {}
        )
    }
}

data class OnboardingButtonState(
    val text: String,
    val startIcon: Int? = null,
    val endIcon: Int? = null,
) {
    constructor() : this(text = "")
}

@Composable
fun OnboardingButton(
    modifier: Modifier = Modifier,
    state: OnboardingButtonState,
    onClick: () -> Unit,
) {
    TextButton(onClick = onClick, modifier = modifier) {
        if (state.startIcon != null) {
            Icon(painterResource(state.startIcon), null)
        }
        Text(
            text = state.text.uppercase(),
            style = MaterialTheme.typography.labelLarge,
        )
        if (state.endIcon != null) {
            Icon(painterResource(state.endIcon), null)
        }
    }
}

val onboardingPages
    @Composable
    get() = listOf(OnboardingPageOne, OnboardingPageTwo, OnboardingPageThree)

val OnboardingPageOne
    @Composable get() = OnboardingItem(
        R.drawable.ic_fresh_produce,
        stringResource(R.string.text_onboarding_fresh_produce_title),
        stringResource(R.string.text_onboarding_fresh_produce_description)
    )

val OnboardingPageTwo
    @Composable get() = OnboardingItem(
        R.drawable.ic_fast_delivery,
        stringResource(R.string.text_onboarding_fast_delivery_title),
        stringResource(R.string.text_onboarding_fast_delivery_description)
    )

val OnboardingPageThree
    @Composable get() = OnboardingItem(
        R.drawable.ic_easy_payments,
        stringResource(R.string.text_onboarding_easy_payments_title),
        stringResource(R.string.text_onboarding_easy_payments_description)
    )
