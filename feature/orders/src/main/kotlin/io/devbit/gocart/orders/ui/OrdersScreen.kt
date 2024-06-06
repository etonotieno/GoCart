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
package io.devbit.gocart.orders.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.devbit.gocart.orders.ui.preview.sampleRecentOrders
import io.devbits.gocart.designsystem.component.PrimaryButton
import io.devbits.gocart.designsystem.theme.GoCartSurface
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.resources.R as resourcesR

@Composable
fun OrdersRoute(
    navigateToPlaceOrder: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OrdersViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    OrdersScreen(
        state = state,
        navigateToPlaceOrder = navigateToPlaceOrder,
        modifier = modifier,
    )
}

@Composable
fun OrdersScreen(
    state: OrdersUiState,
    navigateToPlaceOrder: () -> Unit,
    modifier: Modifier = Modifier,
) {
    GoCartSurface(modifier = modifier) {
        when (state) {
            Empty -> {
                EmptyOrdersScreen(navigateToPlaceOrder)
            }

            Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.size(64.dp))
                }
            }

            is Success -> {
                OrdersUiScreen(state.orders)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OrdersUiScreen(orders: List<UiOrderItem>, modifier: Modifier = Modifier) {
    val density = LocalDensity.current

    val pagerState = rememberPagerState { 2 }

    val tabs = listOf("Recent", "Cancelled")
    var selectedTabIndex by remember { mutableIntStateOf(pagerState.currentPage) }

    LaunchedEffect(selectedTabIndex) {
        // Scroll the HorizontalPager when the selected tab is changed
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        // Change the selected Tab when the HorizontalPager is scrolled
        selectedTabIndex = pagerState.currentPage
    }

    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    Column(modifier = modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            indicator = { tabPositions ->
                if (selectedTabIndex < tabPositions.size) {
                    TabRowDefaults.PrimaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        width = tabWidths[selectedTabIndex],
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp),
                    )
                }
            },
        ) {
            tabs.forEachIndexed { tabIndex, title ->
                val selected = selectedTabIndex == tabIndex
                Tab(
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selected) FontWeight.Bold else null,
                            onTextLayout = { textLayoutResult ->
                                tabWidths[tabIndex] =
                                    with(density) { textLayoutResult.size.width.toDp() }
                            },
                        )
                    },
                    selected = selected,
                    onClick = { selectedTabIndex = tabIndex },
                    selectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        val (recentOrders, cancelledOrders) = remember {
            orders.partition { it.status != OrderStatus.Cancelled }
        }

        HorizontalPager(pagerState) { currentPage ->
            when (currentPage) {
                0 -> RecentOrdersPage(recentOrders = recentOrders)

                1 -> CancelledOrdersPage(cancelledOrders = cancelledOrders)
            }
        }
    }
}

@Composable
private fun RecentOrdersPage(modifier: Modifier = Modifier, recentOrders: List<UiOrderItem>) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(recentOrders, key = { it.id }) {
            Text("Order: ${it.number}: ${it.date}")
        }
    }
}

@Composable
private fun CancelledOrdersPage(modifier: Modifier = Modifier, cancelledOrders: List<UiOrderItem>) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(cancelledOrders, key = { it.id }) {
            Text("Order: ${it.number}: ${it.date}")
        }
    }
}

@Composable
private fun EmptyOrdersScreen(navigateToPlaceOrder: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painterResource(resourcesR.drawable.ic_illustration_orders),
            contentDescription = null,
            modifier = Modifier.size(width = 133.dp, height = 167.dp),
        )

        Spacer(Modifier.height(32.dp))

        Text(
            text = "You haven’t made an order yet!",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.SemiBold),
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Your ongoing and cancelled orders will appear here",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 20.dp),
        )

        Spacer(Modifier.height(64.dp))

        PrimaryButton(
            text = "Place an order",
            onClick = navigateToPlaceOrder,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )
    }
}

@PreviewLightDark
@Composable
private fun OrdersScreenPreview() {
    GoCartTheme {
        OrdersScreen(state = Success(sampleRecentOrders), navigateToPlaceOrder = {})
    }
}

@PreviewLightDark
@Composable
private fun OrdersScreenEmptyPreview() {
    GoCartTheme {
        OrdersScreen(state = Empty, navigateToPlaceOrder = {})
    }
}

@Preview
@Composable
private fun OrdersScreenLoadingPreview() {
    GoCartTheme {
        OrdersScreen(state = Loading, navigateToPlaceOrder = {})
    }
}
