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
package io.devbits.gocart.product.details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.devbits.gocart.product.details.ui.ProductDetailsRoute

internal const val productDetailRoute = "product/detail"

internal const val productIdArg = "productId"

fun NavController.navigateToProductDetails(productId: Int, navOptions: NavOptions? = null) {
    this.navigate("$productDetailRoute/$productId", navOptions)
}

fun NavGraphBuilder.productDetailsScreen(
    onBack: () -> Unit,
    onClickProduct: (Int) -> Unit,
    navigateToCart: () -> Unit,
) {
    composable(
        route = "$productDetailRoute/{$productIdArg}",
        arguments = listOf(navArgument(productIdArg) { type = NavType.IntType }),
    ) {
        ProductDetailsRoute(
            onBack = onBack,
            onClickProduct = onClickProduct,
            navigateToCart = navigateToCart,
        )
    }
}
