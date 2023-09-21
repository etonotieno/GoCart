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
package io.devbits.gocart.homefeed.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import io.devbits.gocart.designsystem.component.Chip
import io.devbits.gocart.designsystem.component.GcSortBottomSheet
import io.devbits.gocart.designsystem.component.ProductCard
import io.devbits.gocart.designsystem.component.PromotionBanner
import io.devbits.gocart.designsystem.component.TertiaryButton
import io.devbits.gocart.designsystem.component.sampleProducts
import io.devbits.gocart.designsystem.model.Product
import io.devbits.gocart.designsystem.model.ProductCategory
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.resources.R as resourcesR

@Composable
fun HomeScreen(
    toCategories: () -> Unit,
    navigateToProduct: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val products by viewModel.products.collectAsStateWithLifecycle()
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    HomeScreen(
        products = products,
        categories = categories,
        onBookmark = {},
        onAddToCart = {},
        navigateToProduct = navigateToProduct,
        onViewAll = toCategories,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    products: List<Product>,
    categories: List<ProductCategory>,
    navigateToProduct: (Int) -> Unit,
    onBookmark: () -> Unit,
    onAddToCart: () -> Unit,
    onViewAll: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    // Default span that fills the entire width.
    val span = GridItemSpan(2)

    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        item(span = { span }) {
            HeaderText()
        }

        item(span = { span }) {
            PromotionBanner(modifier = Modifier.padding(horizontal = 16.dp))
        }

        item(span = { span }) {
            ExploreCategories(categories = categories, onViewAll = onViewAll)
        }

        item(span = { span }) {
            TopSellingProducts(onSort = { showBottomSheet = true })
        }

        // We do not pass the span to the ProductCard items in order to display the ProductCard in
        // a 2 column grid
        items(products.size) { index: Int ->
            // Add padding start for products in the first column & padding end for products in the
            // second column.
            val padding = if ((index % 2) == 0) {
                PaddingValues(start = 16.dp)
            } else {
                PaddingValues(end = 16.dp)
            }
            ProductCard(
                product = products[index],
                onBookmark = onBookmark,
                onAddToCart = onAddToCart,
                onClick = { navigateToProduct(products[index].id) },
                modifier = Modifier.padding(padding),
            )
        }
    }

    if (showBottomSheet) {
        GcSortBottomSheet(
            onDismiss = { showBottomSheet = false },
            onSortChanged = {},
        )
    }
}

@Composable
private fun HeaderText() {
    val isLocalInspection = LocalInspectionMode.current

    val imageLoader = rememberAsyncImagePainter(resourcesR.raw.truck_loading)
    val placeholder = painterResource(resourcesR.drawable.ic_placeholder)

    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(horizontal = 16.dp)
            .height(40.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = if (!isLocalInspection) imageLoader else placeholder,
            contentDescription = null,
        )

        Text(text = "Free delivery within Nairobi")
    }
}

@Composable
private fun ProductCategories(
    categories: List<ProductCategory>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(categories) { category ->
            var selected by remember { mutableStateOf(false) }
            Chip(
                label = category.label,
                selected = selected,
                onClick = { selected = !selected },
            )
        }
    }
}

@Composable
private fun TopSellingProducts(modifier: Modifier = Modifier, onSort: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "TOP SELLING PRODUCTS",
            modifier = Modifier.padding(vertical = 12.dp),
            style = MaterialTheme.typography.bodyMedium,
        )

        TertiaryButton(
            text = "Sort",
            onClick = onSort,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Sort, contentDescription = null)
            },
        )
    }
}

@Composable
private fun ExploreCategories(
    categories: List<ProductCategory>,
    onViewAll: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "EXPLORE CATEGORIES",
                modifier = Modifier.padding(vertical = 12.dp),
                style = MaterialTheme.typography.bodyMedium,
            )

            TertiaryButton(text = "View All", onClick = onViewAll)
        }

        ProductCategories(categories = categories)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun HomeScreenPreview() {
    GoCartTheme {
        HomeScreen(
            products = sampleProducts,
            categories = ProductCategory.values().toList(),
            onBookmark = {},
            onAddToCart = {},
            navigateToProduct = {},
            onViewAll = {},
        )
    }
}
