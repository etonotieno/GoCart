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
package io.devbits.gocart.cart.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.devbits.gocart.designsystem.component.GCTextField
import io.devbits.gocart.designsystem.component.GCTopAppBar
import io.devbits.gocart.designsystem.component.GoCartAlert
import io.devbits.gocart.designsystem.component.PrimaryButton
import io.devbits.gocart.designsystem.component.SecondaryButton
import io.devbits.gocart.designsystem.component.ShoppingCartCard
import io.devbits.gocart.designsystem.component.TertiaryButton
import io.devbits.gocart.designsystem.component.sampleProducts
import io.devbits.gocart.designsystem.model.Product
import io.devbits.gocart.designsystem.theme.GoCartSurface
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.resources.R as resourcesR

@Composable
fun ShoppingCartRoute(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ShoppingCartViewModel = hiltViewModel(),
) {
    val products by viewModel.products.collectAsStateWithLifecycle()
    ShoppingCartScreen(
        products = products,
        modifier = modifier,
        onBack = onBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartScreen(
    products: List<Product>,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var coupon by remember { mutableStateOf("") }
    var couponIsValid by remember { mutableStateOf(false) }

    val isAlert by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            GCTopAppBar(
                title = { Text("My Cart") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
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
        ) {
            if (isAlert) {
                item {
                    GoCartAlert(
                        icon = Icons.Outlined.Info,
                        text = "You can only order a maximum quantity of 3 in your item.",
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "3 items in your cart")

                    TertiaryButton(
                        text = "Clear All",
                        onClick = {},
                        contentColor = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            items(products) { product ->
                ShoppingCartCard(
                    product = product,
                    onBookmark = { },
                    onDelete = { },
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    GCTextField(
                        value = coupon,
                        onValueChange = { coupon = it },
                        label = {
                            Text(text = "Coupon Code")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.PhoneAndroid,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                            )
                        },
                        trailingIcon = {
                            if (couponIsValid) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                        ),
                        modifier = Modifier.weight(1f),
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    SecondaryButton(
                        text = "Apply",
                        onClick = { couponIsValid = true },
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Column {
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        headlineContent = {
                            Text(
                                text = "Cart Total",
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        },
                        trailingContent = {
                            Text(
                                text = "KSH 483.00",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        },
                    )
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        headlineContent = {
                            Text(
                                text = "Delivery charges",
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        },
                        trailingContent = {
                            Text(
                                text = "Free!!",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary,
                            )
                        },
                    )
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        headlineContent = {
                            Text(
                                text = "Coupon",
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        },
                        trailingContent = {
                            Text(
                                text = "Ksh -100.00",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        },
                    )
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        headlineContent = {
                            Text(
                                text = "TOTAL",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                            )
                        },
                        trailingContent = {
                            Text(
                                text = "Ksh 383.00",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                            )
                        },
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                PrimaryButton(
                    text = "Checkout",
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun EmptyContent(modifier: Modifier = Modifier, navigateToCategories: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = resourcesR.drawable.ic_illustration_cart),
                contentDescription = null,
                modifier = Modifier.height(200.dp),
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Your bag is empty",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "It looks like there no items added in your shopping cart.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
        }

        PrimaryButton(
            text = "Browse Products",
            onClick = navigateToCategories,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(70.dp))
    }
}

@Preview
@Composable
private fun ShoppingCartScreenPreview() {
    GoCartTheme {
        @Suppress("MagicNumber")
        ShoppingCartScreen(products = sampleProducts.take(5), onBack = {})
    }
}

@Preview
@Composable
private fun EmptyContentPreview() {
    GoCartTheme {
        GoCartSurface {
            EmptyContent(navigateToCategories = {})
        }
    }
}
