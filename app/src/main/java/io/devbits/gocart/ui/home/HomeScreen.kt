package io.devbits.gocart.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.devbits.gocart.composeui.components.GoCartBottomAppBar
import io.devbits.gocart.composeui.components.GoCartTopAppBar
import io.devbits.gocart.composeui.components.navigationItems
import io.devbits.gocart.composeui.theme.GoCartTheme

@Composable
fun HomeRoute(modifier: Modifier) {
    HomeScreen(modifier)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            GoCartTopAppBar(
                onClickNavigation = {},
                onSearch = {},
                onCheckout = {},
            )
        },
        bottomBar = {
            GoCartBottomAppBar(
                selectedRoute = "Home",
                navigationRoutes = navigationItems,
                onNavigationSelected = { _ -> },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
        ) {

            Text(
                text = "Free delivery within Nairobi",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    GoCartTheme {
        HomeScreen()
    }
}