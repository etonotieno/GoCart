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

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.devbits.gocart.designsystem.theme.GoCartTheme
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
private fun OnboardingHorizontalPagerPreview() {
    GoCartTheme {
        OnboardingHorizontalPager(onOnboarded = {})
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPagerIndicator(
    pages: List<OnboardingItem>,
    pagerState: PagerState,
    onOnboarded: () -> Unit,
    modifier: Modifier = Modifier,
    skipIntro: Boolean = false,
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
                    startIcon = R.drawable.ic_left,
                ) to OnboardingButtonState(
                    text = next,
                    endIcon = R.drawable.ic_right,
                )

                2 -> OnboardingButtonState(
                    text = back,
                    startIcon = R.drawable.ic_left,
                ) to OnboardingButtonState(text = done)

                else -> OnboardingButtonState() to OnboardingButtonState()
            }
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(8.dp))

        if (skipIntro) {
            Button(onClick = onOnboarded) {
                Text(stringResource(R.string.text_button_skip_intro).uppercase())
            }
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

private const val ANIMATION_DURATION = 400

@Composable
private fun Indicator(
    pageSize: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(pageSize) { page ->
            val isSelected = currentPage == page

            val indicatorColor: Color by animateColorAsState(
                targetValue = if (isSelected) {
                    MaterialTheme.colorScheme.secondary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
                animationSpec = tween(
                    durationMillis = ANIMATION_DURATION,
                ),
                label = "anim:indicatorColor",
            )

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(indicatorColor),
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
@Composable
private fun OnboardingPagerIndicatorPreview() {
    GoCartTheme {
        val pages = onboardingPages
        OnboardingPagerIndicator(
            pages = pages,
            skipIntro = true,
            pagerState = rememberPagerState { pages.size },
            onOnboarded = {},
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
    state: OnboardingButtonState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
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
        stringResource(R.string.text_onboarding_fresh_produce_description),
    )

val OnboardingPageTwo
    @Composable get() = OnboardingItem(
        R.drawable.ic_fast_delivery,
        stringResource(R.string.text_onboarding_fast_delivery_title),
        stringResource(R.string.text_onboarding_fast_delivery_description),
    )

val OnboardingPageThree
    @Composable get() = OnboardingItem(
        R.drawable.ic_easy_payments,
        stringResource(R.string.text_onboarding_easy_payments_title),
        stringResource(R.string.text_onboarding_easy_payments_description),
    )
