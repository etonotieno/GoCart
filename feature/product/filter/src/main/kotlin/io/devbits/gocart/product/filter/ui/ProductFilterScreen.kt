/*
 * Copyright 2024 Eton Otieno
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
package io.devbits.gocart.product.filter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.RangeSliderState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.devbits.gocart.designsystem.component.Chip
import io.devbits.gocart.designsystem.component.GCTopAppBar
import io.devbits.gocart.designsystem.model.ProductCategory
import io.devbits.gocart.designsystem.spacerItem
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.designsystem.theme.go_cart_sinopia
import io.devbits.gocart.resources.R as resourcesR
import kotlin.math.roundToInt

@Composable
fun ProductFilterRoute(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductFilterViewModel = hiltViewModel(),
) {
    val produce by viewModel.produce.collectAsStateWithLifecycle()
    ProductFilterScreen(
        produce = produce,
        onBack = onBack,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFilterScreen(
    produce: List<String>,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val selectedProduce = remember { mutableStateListOf<String>() }

    val sliderRange by remember {
        mutableStateOf(50f..25_000f)
    }

    val sliderState = remember {
        RangeSliderState(
            activeRangeStart = sliderRange.start,
            activeRangeEnd = sliderRange.endInclusive,
            valueRange = sliderRange.start..sliderRange.endInclusive,
        )
    }

    val selectedCategories = remember { mutableStateListOf<ProductCategory>() }

    val filterIsApplied by remember {
        derivedStateOf {
            selectedProduce.isNotEmpty() || selectedCategories.isNotEmpty() ||
                (
                    sliderState.activeRangeStart != sliderRange.start ||
                        sliderState.activeRangeEnd != sliderRange.endInclusive
                    )
        }
    }

    Scaffold(
        topBar = {
            GCTopAppBar(
                title = { Text(text = "Filters") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(resourcesR.drawable.ic_outlined_close),
                            contentDescription = null,
                        )
                    }
                },
                actions = {
                    TextButton(onClick = {}, enabled = filterIsApplied) {
                        Text(text = "Reset all")
                    }
                },
            )
        },
        modifier = modifier,
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 16.dp),
        ) {
            item {
                ProduceFilterItem(
                    produce = produce,
                    selectedProduce = selectedProduce,
                    onClickProduce = { item ->
                        if (item in selectedProduce) {
                            selectedProduce.remove(item)
                        } else {
                            selectedProduce.add(item)
                        }
                    },
                )
            }

            item {
                HorizontalDivider(modifier = Modifier.padding(top = 32.dp, bottom = 16.dp))
            }

            item {
                PriceRangeItem(state = sliderState)
            }

            item {
                HorizontalDivider(modifier = Modifier.padding(top = 32.dp, bottom = 16.dp))
            }

            item {
                ProductCategoriesItem(
                    produce = ProductCategory.entries,
                    selectedProduce = selectedCategories,
                    onClickProduce = { item ->
                        if (item in selectedCategories) {
                            selectedCategories.remove(item)
                        } else {
                            selectedCategories.add(item)
                        }
                    },
                )
            }

            spacerItem(modifier = Modifier.size(46.dp))

            item {
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    enabled = filterIsApplied,
                ) {
                    Text(text = "Apply filter")
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProduceFilterItem(
    produce: List<String>,
    selectedProduce: List<String?>,
    onClickProduce: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Produce",
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.size(16.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            produce.forEach { item ->
                Chip(
                    label = item,
                    selected = item in selectedProduce,
                    onClick = { onClickProduce(item) },
                    leadingIcon = if (item in selectedProduce) {
                        {
                            Icon(
                                painter = painterResource(resourcesR.drawable.ic_outlined_check),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                    } else {
                        null
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceRangeItem(
    state: RangeSliderState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = "Price Range",
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                text = "Ksh 50 - Ksh 25,000",
                color = go_cart_sinopia,
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        PriceRangeSlider(state = state)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceRangeSlider(
    state: RangeSliderState,
    modifier: Modifier = Modifier,
) {
    var start by remember(state.activeRangeStart) {
        mutableStateOf("${state.activeRangeStart.roundToInt()}")
    }

    var end by remember(state.activeRangeEnd) {
        mutableStateOf("${state.activeRangeEnd.roundToInt()}")
    }

    Row(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = start,
            onValueChange = { value ->
                start = value
                state.activeRangeStart = value.toFloat()
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.widthIn(max = 88.dp),
        )

        RangeSlider(
            state = state,
            modifier = Modifier.weight(1f),
        )

        OutlinedTextField(
            value = end,
            onValueChange = { value ->
                end = value
                state.activeRangeEnd = value.toFloat()
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.widthIn(max = 88.dp),
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductCategoriesItem(
    produce: List<ProductCategory>,
    selectedProduce: List<ProductCategory>,
    onClickProduce: (ProductCategory) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Top selling categories",
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.size(16.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            produce.forEach { item ->
                Chip(
                    label = item.label,
                    selected = item in selectedProduce,
                    onClick = { onClickProduce(item) },
                    leadingIcon = if (item in selectedProduce) {
                        {
                            Icon(
                                painter = painterResource(resourcesR.drawable.ic_outlined_check),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                    } else {
                        null
                    },
                )
            }
        }
    }
}

val produceList = listOf("Fresh", "Green", "Colored", "Organic", "Bundled", "Pack", "Dried")

@Preview
@Composable
private fun ProductFilterScreenPreview() {
    GoCartTheme {
        ProductFilterScreen(
            produce = produceList,
            onBack = {},
        )
    }
}
