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
package io.devbits.gocart.authentication.ui.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.devbits.gocart.authentication.R
import io.devbits.gocart.designsystem.component.BackgroundWhitePreview
import io.devbits.gocart.designsystem.component.ThemePreviews
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.resources.R as resourcesR

@Composable
fun LocationPermissionRoute(
    onSkip: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LocationViewModel = hiltViewModel(),
) {
    LocationPermissionScreen(
        onSkip = {
            onSkip()
            viewModel.verify()
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationPermissionScreen(
    onSkip: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {},
                actions = {
                    IconButton(onClick = onSkip) {
                        Icon(
                            imageVector = Icons.Filled.Cancel,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = resourcesR.drawable.ic_illustration_location),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.height(64.dp))

            Text(
                text = "Enable Location",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.location_text),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Share Location")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onSkip, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Skip")
            }
        }
    }
}

@Composable
@BackgroundWhitePreview
@ThemePreviews
private fun LocationPermissionScreenPreview() {
    GoCartTheme {
        LocationPermissionScreen(onSkip = {})
    }
}
