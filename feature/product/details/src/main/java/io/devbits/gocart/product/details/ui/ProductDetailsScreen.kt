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
package io.devbits.gocart.product.details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.devbits.gocart.designsystem.component.GCTopAppBar
import io.devbits.gocart.designsystem.component.PrimaryButton
import io.devbits.gocart.designsystem.component.ProductCard
import io.devbits.gocart.designsystem.component.ProductDetailsCard
import io.devbits.gocart.designsystem.component.SecondaryGrayButton
import io.devbits.gocart.designsystem.component.sampleProducts
import io.devbits.gocart.designsystem.model.Product
import io.devbits.gocart.designsystem.theme.GoCartTheme

@Composable
fun ProductDetailsRoute(
    onBack: () -> Unit,
    onClickProduct: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = hiltViewModel(),
) {
    val relatedProducts by viewModel.products.collectAsStateWithLifecycle()
    ProductDetailsScreen(
        product = viewModel.product,
        relatedProducts = relatedProducts,
        onBookmark = {},
        onAddToCart = {},
        onAddToWishlist = {},
        onClickProduct = onClickProduct,
        onBack = onBack,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    product: Product,
    relatedProducts: List<Product>,
    onClickProduct: (Int) -> Unit,
    onBookmark: () -> Unit,
    onAddToCart: () -> Unit,
    onAddToWishlist: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            GCTopAppBar(
                title = { Text(product.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Image(
                    painter = painterResource(product.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                )
            }

            item {
                ProductDetailsCard(product = product)
            }

            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(horizontal = 32.dp),
                ) {
                    PrimaryButton(
                        text = "Add to Cart",
                        onClick = onAddToCart,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                    )

                    SecondaryGrayButton(
                        text = "Add to Wishlist",
                        onClick = onAddToWishlist,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                    )
                }
            }

            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(horizontal = 16.dp),
                ) {
                    Text(
                        text = "Product description",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        text = product.description,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify,
                    )
                }
            }

            item {
                Text(
                    text = "Related Products",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .height(40.dp)
                        .padding(horizontal = 16.dp),
                )
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                ) {
                    items(relatedProducts) { relatedProduct ->
                        ProductCard(
                            product = relatedProduct,
                            onBookmark = onBookmark,
                            onAddToCart = onAddToCart,
                            onClick = { onClickProduct(relatedProduct.id) },
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProductDetailsScreenPreview() {
    GoCartTheme {
        ProductDetailsScreen(
            product = sampleProducts[0],
            relatedProducts = sampleProducts,
            onBookmark = {},
            onAddToCart = {},
            onAddToWishlist = {},
            onClickProduct = {},
            onBack = {},
        )
    }
}
