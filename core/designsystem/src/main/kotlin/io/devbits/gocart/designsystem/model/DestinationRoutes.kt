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
package io.devbits.gocart.designsystem.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.ui.graphics.vector.ImageVector

enum class DestinationRoutes(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val titleText: String,
) {
    HOME(
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Outlined.Home,
        titleText = "Home",
    ),
    SERVICES(
        selectedIcon = Icons.AutoMirrored.Filled.Chat,
        unselectedIcon = Icons.AutoMirrored.Outlined.Chat,
        titleText = "Services",
    ),
    ORDERS(
        selectedIcon = Icons.Default.ShoppingBag,
        unselectedIcon = Icons.Outlined.ShoppingBag,
        titleText = "Orders",
    ),
    FAVORITES(
        selectedIcon = Icons.Default.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        titleText = "Favorites",
    ),
}
