package io.devbits.gocart.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.devbits.gocart.composeui.components.GoCartNavBar
import io.devbits.gocart.composeui.components.GoCartNavDrawer
import io.devbits.gocart.composeui.components.GoCartTopAppBar
import io.devbits.gocart.composeui.components.navigationItems
import io.devbits.gocart.composeui.theme.GoCartTheme
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    modifier: Modifier,
    isLoggedIn: Boolean,
    onProfileClick: () -> Unit,
    onSignUp: () -> Unit,
    onLogout: () -> Unit,
    onClickMyAddresses: () -> Unit,
    onClickPayments: () -> Unit,
    onClickSpecialOffers: () -> Unit,
    onClickSettings: () -> Unit,
    onClickHelp: () -> Unit,
) {
    HomeScreen(
        modifier = modifier,
        isLoggedIn = isLoggedIn,
        onProfileClick = onProfileClick,
        onSignUp = onSignUp,
        onLogout = onLogout,
        onClickMyAddresses = onClickMyAddresses,
        onClickPayments = onClickPayments,
        onClickSpecialOffers = onClickSpecialOffers,
        onClickSettings = onClickSettings,
        onClickHelp = onClickHelp,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    isLoggedIn: Boolean,
    onProfileClick: () -> Unit,
    onSignUp: () -> Unit,
    onLogout: () -> Unit,
    onClickMyAddresses: () -> Unit,
    onClickPayments: () -> Unit,
    onClickSpecialOffers: () -> Unit,
    onClickSettings: () -> Unit,
    onClickHelp: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            GoCartNavDrawer(
                isLoggedIn = isLoggedIn,
                onProfileClick = onProfileClick,
                onSignUp = onSignUp,
                onLogout = onLogout,
                onClickMyAddresses = onClickMyAddresses,
                onClickPayments = onClickPayments,
                onClickSpecialOffers = onClickSpecialOffers,
                onClickSettings = onClickSettings,
                onClickHelp = onClickHelp,
            )
        },
        drawerState = drawerState,
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                GoCartTopAppBar(
                    onClickNavigation = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    },
                    onSearch = {},
                    onCheckout = {},
                )
            },
            bottomBar = {
                GoCartNavBar(
                    selectedRoute = "Home",
                    navigationRoutes = navigationItems,
                    onNavigationSelected = { _ -> },
                )
            },
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
}

@Preview
@Composable
fun HomeScreenPreview() {
    GoCartTheme {
        HomeScreen(
            isLoggedIn = true,
            onProfileClick = {},
            onSignUp = {},
            onLogout = {},
            onClickMyAddresses = {},
            onClickPayments = {},
            onClickSpecialOffers = {},
            onClickSettings = {},
            onClickHelp = {},
        )
    }
}