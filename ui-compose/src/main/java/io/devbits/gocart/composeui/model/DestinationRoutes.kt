package io.devbits.gocart.composeui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.outlined.Chat
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
        selectedIcon = Icons.Default.Chat,
        unselectedIcon = Icons.Outlined.Chat,
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
