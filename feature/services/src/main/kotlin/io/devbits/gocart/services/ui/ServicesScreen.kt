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
@file:OptIn(ExperimentalMaterial3Api::class)

package io.devbits.gocart.services.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.resources.R as resourcesR

@Composable
fun ServicesRoute(modifier: Modifier = Modifier) {
    ServicesScreen(modifier = modifier)
}

@Composable
fun ServicesScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier, color = MaterialTheme.colorScheme.surfaceVariant) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp),
        ) {
            SectionHeader()

            SupportOptions()
        }
    }
}

@Composable
private fun SectionHeader(modifier: Modifier = Modifier) {
    Text(
        text = "QUESTIONS",
        style = MaterialTheme.typography.titleSmall.copy(
            fontSize = 12.sp,
            lineHeight = 20.sp,
        ),
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    )
}

@Composable
fun SupportOptions(modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(horizontal = 16.dp), shape = RoundedCornerShape(8.dp)) {
        SupportServicesItem(
            headline = "Live Chat",
            supporting = "Sun-Fri 8:00am-5:00pm",
            leadingIcon = resourcesR.drawable.ic_outlined_chat,
            trailingIcon = resourcesR.drawable.ic_outlined_chevronright,
            onClick = {},
        )

        HorizontalDivider()

        SupportServicesItem(
            headline = "Email Us",
            supporting = "help@gocart.com",
            leadingIcon = resourcesR.drawable.ic_outlined_email,
            trailingIcon = resourcesR.drawable.ic_outlined_chevronright,
            onClick = {},
        )

        HorizontalDivider()

        SupportServicesItem(
            headline = "Call Support Line",
            supporting = "+254(700456xx2)",
            leadingIcon = resourcesR.drawable.ic_outlined_call,
            trailingIcon = resourcesR.drawable.ic_outlined_chevronright,
            onClick = {},
        )
    }
}

@Composable
private fun SupportServicesItem(
    headline: String,
    supporting: String,
    @DrawableRes leadingIcon: Int,
    @DrawableRes trailingIcon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        headlineContent = {
            Text(headline)
        },
        supportingContent = {
            Text(supporting)
        },
        leadingContent = {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        },
        trailingContent = {
            Icon(
                painter = painterResource(trailingIcon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },
        modifier = modifier.clickable { onClick() },
    )
}

@PreviewLightDark
@Composable
private fun ServicesScreenPreview() {
    GoCartTheme {
        ServicesScreen()
    }
}
