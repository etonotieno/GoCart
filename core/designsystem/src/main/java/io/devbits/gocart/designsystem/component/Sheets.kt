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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.devbits.gocart.core.model.SortOption
import io.devbits.gocart.designsystem.model.Country
import io.devbits.gocart.designsystem.model.defaultCountries

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GcSortBottomSheet(
    onDismiss: () -> Unit,
    onSortChanged: (SortOption) -> Unit,
    modifier: Modifier = Modifier,
    sortOptions: List<SortOption> = SortOption.entries,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    var sortOption by remember { mutableStateOf(SortOption.TopSelling) }

    GcBottomSheet(
        onDismiss = onDismiss,
        sheetState = sheetState,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Sort by",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp),
        )

        sortOptions.forEach {
            val selected = sortOption == it
            ListItem(
                modifier = Modifier
                    .clickable {
                        onSortChanged(it)
                        sortOption = it
                    },
                headlineContent = {
                    Text(text = it.label)
                },
                trailingContent = {
                    RadioButton(
                        selected = selected,
                        onClick = {
                            onSortChanged(it)
                            sortOption = it
                        },
                    )
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesBottomSheet(
    onDismiss: () -> Unit,
    onCheck: (Country) -> Unit,
    modifier: Modifier = Modifier,
    countries: List<Country> = defaultCountries,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    var country by remember { mutableStateOf("") }
    var selectedCountry by remember { mutableStateOf(defaultCountries.first()) }

    GcBottomSheet(
        onDismiss = onDismiss,
        sheetState = sheetState,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Countries",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        )

        TextField(
            value = country,
            onValueChange = { country = it },
            placeholder = { Text(text = "Search") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        )

        countries.forEach {
            val selected = selectedCountry == it
            ListItem(
                modifier = Modifier
                    .clickable {
                        selectedCountry = it
                        onCheck(selectedCountry)
                    },
                headlineContent = {
                    GCStyledTextPair(
                        pair = Pair(
                            first = it.name,
                            second = "(+${it.code})",
                        ),
                        style = SpanStyle(color = LocalContentColor.current.copy(alpha = 0.4f)),
                        textStyle = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                    )
                },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                },
                trailingContent = {
                    if (selected) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GcBottomSheet(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    content: @Composable (ColumnScope.() -> Unit),
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = modifier,
        sheetState = sheetState,
        content = content,
    )
}
