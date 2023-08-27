package io.devbits.gocart.homefeed.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.devbits.gocart.composeui.components.GoCartNavBar
import io.devbits.gocart.composeui.components.GoCartNavDrawerContent
import io.devbits.gocart.composeui.components.GoCartTopAppBar
import io.devbits.gocart.composeui.model.DestinationRoutes
import io.devbits.gocart.composeui.model.NavDrawerItem
import io.devbits.gocart.composeui.theme.GoCartTheme
import io.devbits.gocart.core.data.UserPreferences
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    onClickHeader: () -> Unit,
    onSignUp: () -> Unit,
    onNavigationSelected: (DestinationRoutes) -> Unit,
    onDrawerItemClick: (NavDrawerItem) -> Unit,
    preferences: UserPreferences,
    viewModel: HomeViewModel = viewModel(initializer = { HomeViewModel(preferences) }),
) {
    val isLoggedIn by viewModel.isLoggedIn.collectAsState(false)

    HomeScreen(
        modifier = modifier,
        isLoggedIn = isLoggedIn,
        onClickHeader = onClickHeader,
        onSignUp = onSignUp,
        onNavigationSelected = onNavigationSelected,
        onDrawerItemClick = onDrawerItemClick,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    isLoggedIn: Boolean,
    onClickHeader: () -> Unit,
    onSignUp: () -> Unit,
    onNavigationSelected: (DestinationRoutes) -> Unit,
    onDrawerItemClick: (NavDrawerItem) -> Unit,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            GoCartNavDrawerContent(
                isLoggedIn = isLoggedIn,
                onClickHeader = {
                    scope.launch { drawerState.close() }
                    onClickHeader()
                },
                onSignUp = {
                    scope.launch { drawerState.close() }
                    onSignUp()
                },
                items = NavDrawerItem.values().asList(),
                onClick = onDrawerItemClick,
            )
        },
        drawerState = drawerState,
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                // Hide during onboarding and authentication flows
                GoCartTopAppBar(
                    onClickNavigation = {
                        scope.launch {
                            if (drawerState.isClosed) drawerState.open() else drawerState.close()
                        }
                    },
                    onSearch = {},
                    onCheckout = {},
                )
            },
            bottomBar = {
                // Hide during onboarding and authentication flows
                GoCartNavBar(
                    navigationRoutes = DestinationRoutes.values().asList(),
                    onNavigationSelected = onNavigationSelected,
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
            onClickHeader = {},
            onSignUp = {},
            onNavigationSelected = {},
            onDrawerItemClick = {},
        )
    }
}
