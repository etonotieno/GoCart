package io.devbits.gocart.composeui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import io.devbits.gocart.composeui.model.DestinationRoutes
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
                    tint = MaterialTheme.colorScheme.primary
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

private fun NavDestination?.isRouteDestination(destination: DestinationRoutes) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

// TODO: Refactor to component
@Composable
fun GoCartNavBar(
    navigationRoutes: List<DestinationRoutes>,
    onNavigationSelected: (DestinationRoutes) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
        windowInsets = WindowInsets.navigationBars,
    ) {
        navigationRoutes.forEach { route ->
            val selected = currentDestination.isRouteDestination(route)
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (selected) route.selectedIcon else route.unselectedIcon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = route.titleText)
                },
                selected = selected,
                onClick = { onNavigationSelected(route) },
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
fun GoCartNavBarPreview() {
    GoCartTheme {
        GoCartNavBar(
            navigationRoutes = DestinationRoutes.values().asList(),
            onNavigationSelected = { _ -> },
            currentDestination = null,
        )
    }
}
