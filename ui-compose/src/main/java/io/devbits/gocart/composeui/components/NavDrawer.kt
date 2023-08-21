package io.devbits.gocart.composeui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.devbits.gocart.composeui.theme.GoCartTheme
import io.devbits.gocart.resources.R as resourcesR

@Composable
fun GoCartNavDrawer(
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
    ModalDrawerSheet {
        NavHeader(
            isLoggedIn = isLoggedIn,
            onSignUp = onSignUp,
            onProfileClick = onProfileClick,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        DrawerItem(
            icon = Icons.Outlined.LocationOn,
            text = "My Addresses",
            onClick = onClickMyAddresses,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        DrawerItem(
            icon = Icons.Outlined.CreditCard,
            text = "Digital Payments",
            onClick = onClickPayments,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        DrawerItem(
            icon = Icons.Outlined.LocalOffer,
            text = "Special Offers",
            onClick = onClickSpecialOffers,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        DrawerItem(
            icon = Icons.Outlined.Settings,
            text = "Settings",
            onClick = onClickSettings,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        DrawerItem(
            icon = Icons.Outlined.HelpOutline,
            text = "Help & FAQ",
            onClick = onClickHelp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        if (isLoggedIn) {
            DrawerItem(
                icon = Icons.Outlined.Logout,
                text = "Logout",
                onClick = onLogout,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
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
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isLoggedIn) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .clickable { onProfileClick() }
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
            icon = {
                Icon(
                    imageVector = Icons.Outlined.PersonOutline,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
            onClick = onSignUp,
            modifier = modifier,
        )
    }
}

@Composable
fun DrawerItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationDrawerItem(
        label = {
            Text(text = text)
        },
        selected = false,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        },
        onClick = onClick,
        modifier = modifier,
    )
}

@Preview
@Composable
fun NavDrawerAuthenticatedPreview() {
    GoCartTheme {
        GoCartNavDrawer(
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

@Preview
@Composable
fun NavDrawerGuestPreview() {
    GoCartTheme {
        GoCartNavDrawer(
            isLoggedIn = false,
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
