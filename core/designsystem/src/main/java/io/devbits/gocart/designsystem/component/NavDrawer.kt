package io.devbits.gocart.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.devbits.gocart.designsystem.model.NavDrawerItem
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.resources.R as resourcesR

@Composable
fun GoCartNavDrawerContent(
    modifier: Modifier = Modifier,
    items: List<NavDrawerItem>,
    isLoggedIn: Boolean,
    onClickHeader: () -> Unit,
    onClick: (NavDrawerItem) -> Unit,
    onSignUp: () -> Unit,
) {
    // No item is selected by default (the initial value is -1)
    var selectedItem by remember { mutableIntStateOf(-1) }

    Column(modifier = modifier.fillMaxSize()) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))

        NavHeader(
            isLoggedIn = isLoggedIn,
            onSignUp = onSignUp,
            onClickHeader = onClickHeader,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        items
            // TODO: Move logic to a ViewModel
            .asSequence()
            .sortedBy(NavDrawerItem::section)
            .filter { item ->
                if (!isLoggedIn) {
                    // When the user is not logged in, don't display the LOGOUT item
                    item != NavDrawerItem.LOGOUT
                } else {
                    true
                }
            }
            .forEachIndexed { index, item ->
                val selected = index == selectedItem

                if (index == 0) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Divider(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }

                NavigationDrawerItem(
                    label = { Text(item.titleText) },
                    selected = selected,
                    onClick = {
                        selectedItem = index
                        onClick(item)
                    },
                    modifier = Modifier.padding(horizontal = 12.dp),
                    icon = {
                        Icon(
                            imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                            contentDescription = null,
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    )
                )

                if (index == 2) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Divider(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
    }
}

/**
 * Navigation drawer header that displays different content depending on whether the user is
 * [isLoggedIn]
 */
@Composable
private fun NavHeader(
    isLoggedIn: Boolean,
    onSignUp: () -> Unit,
    onClickHeader: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isLoggedIn) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .clickable(onClick = onClickHeader)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id = resourcesR.drawable.ic_avatar_olivia),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(10.dp))

            ListItem(
                headlineContent = {
                    Text(text = "Kevin Kaur", fontWeight = FontWeight.Bold)
                },
                supportingContent = {
                    Text(text = "kevinkaur@gmail.com")
                },
                trailingContent = {
                    Text(
                        text = "edit",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
            )
        }
    } else {
        Spacer(modifier = Modifier.height(52.dp))

        NavigationDrawerItem(
            label = {
                Text(text = "Sign Up", fontWeight = FontWeight.Bold)
            },
            selected = false,
            onClick = onSignUp,
            modifier = modifier,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.PersonOutline,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = MaterialTheme.colorScheme.primary,
                selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                unselectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.onPrimary,
            )
        )
    }
}

@Preview
@Composable
fun NavDrawerAuthenticatedPreview() {
    GoCartTheme {
        GoCartNavDrawerContent(
            isLoggedIn = true,
            items = NavDrawerItem.values().asList(),
            onClick = {},
            onClickHeader = {},
            onSignUp = {},
        )
    }
}

@Preview
@Composable
fun NavDrawerGuestPreview() {
    GoCartTheme {
        GoCartNavDrawerContent(
            isLoggedIn = false,
            items = NavDrawerItem.values().asList(),
            onClick = {},
            onClickHeader = {},
            onSignUp = {},
        )
    }
}
