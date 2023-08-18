package io.devbits.gocart.composeui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.devbits.gocart.composeui.theme.GoCartTheme
import io.devbits.gocart.composeui.theme.go_cart_independence
import io.devbits.gocart.resources.R as resourcesR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoCartTopAppBar(
    onClickNavigation: () -> Unit,
    onSearch: () -> Unit,
    onCheckout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Image(
                painter = painterResource(id = resourcesR.drawable.ic_go_cart_horizontal_color),
                contentDescription = null,
                modifier = Modifier.height(24.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = onClickNavigation) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onClick = onSearch) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = go_cart_independence
                )
            }

            IconButton(onClick = onCheckout) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = null,
                    tint = go_cart_independence
                )
            }
        }
    )
}

@Composable
fun GoCartBottomAppBar(
    selectedRoute: String,
    navigationRoutes: List<Pair<String, ImageVector>>,
    onNavigationSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
        windowInsets = WindowInsets.navigationBars,
    ) {
        for (route in navigationRoutes) {
            NavigationBarItem(
                icon = { Icon(imageVector = route.second, contentDescription = null) },
                label = { Text(text = route.first) },
                selected = selectedRoute == route.first,
                onClick = { onNavigationSelected(route.first) },
            )
        }
    }
}

@Preview
@Composable
fun GoCartTopAppBarPreview() {
    GoCartTheme {
        GoCartTopAppBar(
            onClickNavigation = {},
            onSearch = {},
            onCheckout = {},
        )
    }
}

@Preview
@Composable
fun GoCartBottomAppBarPreview() {
    GoCartTheme {
        GoCartBottomAppBar(
            selectedRoute = "Home",
            navigationRoutes = navigationItems,
            onNavigationSelected = { _ -> },
        )
    }
}

val navigationItems = listOf(
    "Home" to Icons.Filled.Home,
    "Services" to Icons.Filled.MailOutline,
    "Orders" to Icons.Filled.AccountCircle,
    "Favourites" to Icons.Filled.FavoriteBorder
)