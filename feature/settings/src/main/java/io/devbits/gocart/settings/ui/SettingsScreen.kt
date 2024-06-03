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
package io.devbits.gocart.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoMode
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.devbits.gocart.core.datastore.model.AppTheme
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.designsystem.theme.supportsDynamicTheming

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val theme by viewModel.theme.collectAsStateWithLifecycle()
    val useDynamicTheme by viewModel.useDynamicTheme.collectAsStateWithLifecycle()
    SettingsScreen(
        theme = theme,
        onSetTheme = viewModel::setAppTheme,
        useDynamicTheme = useDynamicTheme,
        setDynamicTheme = viewModel::setDynamicTheme,
        onBack = onBack,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    theme: AppTheme,
    onSetTheme: (AppTheme) -> Unit,
    useDynamicTheme: Boolean,
    setDynamicTheme: (Boolean) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
        modifier = modifier,
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 16.dp),
        ) {
            ThemePreference(
                title = "Theme",
                selected = theme,
                onThemeSelected = onSetTheme,
            )

            HorizontalDivider(Modifier.padding(horizontal = 16.dp))

            if (supportsDynamicTheming()) {
                CheckboxPreference(
                    title = "Dynamic Theme",
                    checked = useDynamicTheme,
                    onCheckedChange = setDynamicTheme,
                )

                HorizontalDivider(Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}

@Composable
private fun CheckboxPreference(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
) {
    Surface(modifier = modifier) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                )
                if (!description.isNullOrBlank()) {
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }

            Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        }
    }
}

@Composable
private fun ThemePreference(
    selected: AppTheme,
    onThemeSelected: (AppTheme) -> Unit,
    title: String,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = selected.name,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            Row(Modifier.selectableGroup()) {
                AppThemeButton(
                    icon = Icons.Default.AutoMode,
                    onClick = { onThemeSelected(AppTheme.SYSTEM) },
                    isSelected = selected == AppTheme.SYSTEM,
                )

                AppThemeButton(
                    icon = Icons.Default.LightMode,
                    onClick = { onThemeSelected(AppTheme.LIGHT) },
                    isSelected = selected == AppTheme.LIGHT,
                )

                AppThemeButton(
                    icon = Icons.Default.DarkMode,
                    onClick = { onThemeSelected(AppTheme.DARK) },
                    isSelected = selected == AppTheme.DARK,
                )
            }
        }
    }
}

@Composable
private fun AppThemeButton(
    isSelected: Boolean,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    FilledIconToggleButton(
        checked = isSelected,
        onCheckedChange = { onClick() },
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    GoCartTheme {
        SettingsScreen(
            theme = AppTheme.SYSTEM,
            onSetTheme = {},
            onBack = {},
            useDynamicTheme = false,
            setDynamicTheme = {},
        )
    }
}
