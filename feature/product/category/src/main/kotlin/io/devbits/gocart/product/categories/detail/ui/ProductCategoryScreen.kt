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
package io.devbits.gocart.product.categories.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.devbits.gocart.designsystem.component.GCTopAppBar
import io.devbits.gocart.designsystem.component.GcSortBottomSheet
import io.devbits.gocart.designsystem.component.ProductCard
import io.devbits.gocart.designsystem.component.TertiaryButton
import io.devbits.gocart.designsystem.component.sampleProducts
import io.devbits.gocart.designsystem.fullWidthItem
import io.devbits.gocart.designsystem.model.Product
import io.devbits.gocart.designsystem.model.ProductCategory
import io.devbits.gocart.designsystem.theme.GoCartTheme
import kotlinx.coroutines.launch

@Composable
fun ProductCategoryRoute(
    onBack: () -> Unit,
    navigateToProduct: (Int) -> Unit,
    navigateToCart: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductCategoryViewModel = hiltViewModel(),
) {
    val products by viewModel.products.collectAsStateWithLifecycle()
    ProductCategoryScreen(
        category = viewModel.category,
        products = products,
        onBookmark = {},
        onAddToCart = {},
        onBack = onBack,
        navigateToProduct = navigateToProduct,
        navigateToCart = navigateToCart,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCategoryScreen(
    category: ProductCategory,
    products: List<Product>,
    onBookmark: () -> Unit,
    onAddToCart: () -> Unit,
    onBack: () -> Unit,
    navigateToProduct: (Int) -> Unit,
    navigateToCart: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            GCTopAppBar(
                title = { Text(category.label) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                        )
                    }

                    IconButton(onClick = navigateToCart) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingCart,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
        modifier = modifier,
    ) { contentPadding ->
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            fullWidthItem {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "${category.quantity} Products",
                        modifier = Modifier.weight(1f),
                    )

                    TertiaryButton(
                        text = "Filter",
                        leadingIcon = {
                            Icon(imageVector = Icons.Outlined.FilterAlt, contentDescription = null)
                        },
                        onClick = { },
                    )

                    TertiaryButton(
                        text = "Sort",
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Sort,
                                contentDescription = null,
                            )
                        },
                        onClick = { showBottomSheet = true },
                    )
                }
            }

            items(products.size) { index: Int ->
                ProductCard(
                    product = products[index],
                    onClick = { navigateToProduct(products[index].id) },
                    onBookmark = onBookmark,
                    onAddToCart = onAddToCart,
                    showDelete = true,
                )
            }
        }
    }

    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    if (showBottomSheet) {
        GcSortBottomSheet(
            onDismiss = { showBottomSheet = false },
            onSortChange = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet = false
                    }
                }
            },
        )
    }
}

@Preview
@Composable
private fun ProductCategoryScreenPreview() {
    GoCartTheme {
        ProductCategoryScreen(
            category = ProductCategory.Drinks,
            products = sampleProducts,
            onBookmark = {},
            onAddToCart = {},
            onBack = {},
            navigateToProduct = {},
            navigateToCart = {},
        )
    }
}
