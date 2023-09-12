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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.devbits.gocart.designsystem.theme.GoCartTheme

@Composable
fun GoCartAlert(
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    iconColor: Color,
    containerColor: Color,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = containerColor),
        modifier = modifier,
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            CompositionLocalProvider(LocalContentColor provides iconColor) {
                Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                    icon()
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            text()
        }
    }
}

@Composable
fun GoCartAlert(
    icon: ImageVector,
    text: String,
    iconColor: Color,
    containerColor: Color,
    modifier: Modifier = Modifier,
) {
    GoCartAlert(
        containerColor = containerColor,
        iconColor = iconColor,
        icon = {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(24.dp))
        },
        text = {
            Text(text = text)
        },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun GoCartAlertSuccessPreview() {
    GoCartTheme {
        GoCartAlert(
            icon = Icons.Outlined.Info,
            text = "You can only order a maximum quantity of 3 in your item.",
            iconColor = MaterialTheme.colorScheme.inversePrimary,
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
        )
    }
}
