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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.devbits.gocart.core.model.SortOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GcSortBottomSheet(
    onDismiss: () -> Unit,
    onSortChanged: (SortOption) -> Unit,
    modifier: Modifier = Modifier,
    sortOptions: List<SortOption> = SortOption.values().toList(),
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
