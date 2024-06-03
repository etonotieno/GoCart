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
package io.devbits.gocart.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import io.devbits.gocart.designsystem.model.DestinationRoutes
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.resources.R as resourcesR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoCartTopAppBar(
    onClickNavigation: () -> Unit,
    onSearch: () -> Unit,
    onCheckout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    GCTopAppBar(
        modifier = modifier,
        title = {
            Image(
                painter = painterResource(id = resourcesR.drawable.ic_go_cart_horizontal_color),
                contentDescription = null,
                modifier = Modifier.height(24.dp),
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
                )
            }

            IconButton(onClick = onCheckout) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = null,
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GCTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable (RowScope.() -> Unit) = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(
        navigationIconContentColor = MaterialTheme.colorScheme.primary,
    ),
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = title,
        navigationIcon = navigationIcon,
        actions = actions,
        windowInsets = windowInsets,
        colors = colors,
        scrollBehavior = scrollBehavior,
    )
}

private fun NavDestination?.isRouteDestination(destination: DestinationRoutes) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

@Composable
fun GoCartNavBar(
    navigationRoutes: List<DestinationRoutes>,
    onClickItem: (DestinationRoutes) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
        windowInsets = WindowInsets.navigationBars,
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        navigationRoutes.forEach { route ->
            val selected = currentDestination.isRouteDestination(route)
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (selected) route.selectedIcon else route.unselectedIcon,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(text = route.titleText)
                },
                selected = selected,
                onClick = { onClickItem(route) },
            )
        }
    }
}

@Preview
@Composable
private fun GoCartTopAppBarPreview() {
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
private fun GoCartNavBarPreview() {
    GoCartTheme {
        GoCartNavBar(
            navigationRoutes = DestinationRoutes.entries,
            onClickItem = { _ -> },
            currentDestination = null,
        )
    }
}
