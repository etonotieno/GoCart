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
package io.devbits.gocart.favorites.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.devbits.gocart.designsystem.component.IconContainer
import io.devbits.gocart.designsystem.component.ProductCard
import io.devbits.gocart.designsystem.component.TertiaryButton
import io.devbits.gocart.designsystem.component.sampleProducts
import io.devbits.gocart.designsystem.fullWidthItem
import io.devbits.gocart.designsystem.model.Product
import io.devbits.gocart.designsystem.theme.GoCartSurface
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.favorites.ui.state.Empty
import io.devbits.gocart.favorites.ui.state.FavoritesUiState
import io.devbits.gocart.favorites.ui.state.Loading
import io.devbits.gocart.favorites.ui.state.Success

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    FavoritesScreen(modifier = modifier, state = state)
}

@Composable
fun FavoritesScreen(state: FavoritesUiState, modifier: Modifier = Modifier) {
    GoCartSurface(modifier = modifier.fillMaxSize()) {
        when (state) {
            Empty -> {
                EmptyFavoritesScreen()
            }

            Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.size(64.dp))
                }
            }

            is Success -> {
                FavoritesUiScreen(state.products)
            }
        }
    }
}

@Composable
fun FavoritesUiScreen(
    products: List<Product>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
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
                    text = "${products.size} Products",
                    modifier = Modifier.weight(1f),
                )

                TertiaryButton(
                    text = "Clear All",
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    },
                    onClick = { },
                )
            }
        }

        items(products.size) { index: Int ->
            ProductCard(
                product = products[index],
                onAddToCart = {},
                onClick = {},
                showDelete = true,
                action = {
                    IconContainer(
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .padding(top = 8.dp, end = 8.dp)
                            .size(32.dp)
                            .clip(CircleShape)
                            .align(Alignment.TopEnd)
                            .clickable {},
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(16.dp),
                        )
                    }
                },
            )
        }
    }
}

@Composable
fun EmptyFavoritesScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        IconContainer(
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
        ) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(36.dp),
            )
        }

        Spacer(Modifier.height(40.dp))

        Text(
            text = "No items added yet!",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.SemiBold),
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Create a wishlist to save your favourite items.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 20.dp),
        )
    }
}

@PreviewLightDark
@Composable
private fun FavoritesScreenPreview() {
    GoCartTheme {
        FavoritesScreen(state = Success(sampleProducts))
    }
}

@PreviewLightDark
@Composable
private fun FavoritesScreenEmptyPreview() {
    GoCartTheme {
        FavoritesScreen(state = Empty)
    }
}

@Preview
@Composable
private fun FavoritesScreenLoadingPreview() {
    GoCartTheme {
        FavoritesScreen(state = Loading)
    }
}
