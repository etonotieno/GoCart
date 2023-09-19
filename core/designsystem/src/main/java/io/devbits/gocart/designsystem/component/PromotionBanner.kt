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
package io.devbits.gocart.designsystem.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.designsystem.theme.go_cart_orange_yellow
import io.devbits.gocart.resources.R as resourcesR
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val PAGE_COUNT = 3
private const val SCROLL_DELAY = 5_000L

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PromotionBanner(
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState(pageCount = { PAGE_COUNT }),
) {
    LaunchedEffect(key1 = pagerState.currentPage) {
        launch {
            delay(SCROLL_DELAY)
            with(pagerState) {
                val target = if (currentPage < PAGE_COUNT - 1) currentPage + 1 else 0
                scrollToPage(page = target)
            }
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        HorizontalPager(state = pagerState) { currentPage ->
            val image = when (currentPage) {
                0 -> resourcesR.drawable.ic_promotions_bundle_offer
                1 -> resourcesR.drawable.ic_promotions_fifty_off
                2 -> resourcesR.drawable.ic_promotions_forty_off
                else -> resourcesR.drawable.ic_promotions_forty_off
            }
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth(),
                contentScale = ContentScale.Companion.Crop,
            )
        }

        Indicator(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            pageCount = pagerState.pageCount,
            currentPage = pagerState.currentPage,
            indicatorSize = 12.dp,
            colors = IndicatorDefaults.indicatorColors(
                color = Color.Transparent,
                selectedColor = go_cart_orange_yellow,
            ),
            border = true,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PromotionBannerPreview() {
    GoCartTheme {
        PromotionBanner()
    }
}
