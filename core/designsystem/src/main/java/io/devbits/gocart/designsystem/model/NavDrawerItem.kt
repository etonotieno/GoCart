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

import androidx.annotation.IntRange
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavDrawerItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val titleText: String,
    @IntRange(from = 1, to = 2) val section: Int,
) {
    ADDRESS(
        selectedIcon = Icons.Default.LocationOn,
        unselectedIcon = Icons.Outlined.LocationOn,
        titleText = "My Addresses",
        section = 1,
    ),
    PAYMENTS(
        selectedIcon = Icons.Default.CreditCard,
        unselectedIcon = Icons.Outlined.CreditCard,
        titleText = "Digital Payments",
        section = 1,
    ),
    OFFERS(
        selectedIcon = Icons.Default.LocalOffer,
        unselectedIcon = Icons.Outlined.LocalOffer,
        titleText = "Special Offers",
        section = 1,
    ),
    SETTINGS(
        selectedIcon = Icons.Default.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        titleText = "Settings",
        section = 2,
    ),
    HELP(
        selectedIcon = Icons.Default.Help,
        unselectedIcon = Icons.Outlined.HelpOutline,
        titleText = "Help & FAQ",
        section = 2,
    ),
    LOGOUT(
        selectedIcon = Icons.Default.Logout,
        unselectedIcon = Icons.Outlined.Logout,
        titleText = "Logout",
        section = 2,
    ),
}
