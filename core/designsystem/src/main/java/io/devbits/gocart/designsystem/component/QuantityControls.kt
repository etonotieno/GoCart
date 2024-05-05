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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.designsystem.thenIf

@Composable
fun QuantityControl(
    onUpdate: (quantity: Int) -> Unit,
    modifier: Modifier = Modifier,
    showDelete: Boolean = false,
) {
    var quantity by remember { mutableIntStateOf(0) }
    val enabled by remember { derivedStateOf { quantity > 0 } }
    val latestOnUpdate: (quantity: Int) -> Unit by rememberUpdatedState(onUpdate)

    LaunchedEffect(quantity) {
        latestOnUpdate(quantity)
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        @Suppress("MagicNumber")
        val alpha = if (enabled) 1f else 0.3f

        val decrement = Modifier.clickable(enabled = enabled) { quantity-- }

        if (showDelete) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error.copy(alpha = alpha),
                modifier = Modifier.thenIf(enabled) { decrement },
            )
        } else {
            Icon(
                imageVector = Icons.Default.RemoveCircle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary.copy(alpha = alpha),
                modifier = Modifier.thenIf(enabled) { decrement },
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "$quantity",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            imageVector = Icons.Default.AddCircle,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { quantity++ },
        )
    }
}

@Composable
fun QuantityControl(
    quantity: Int,
    onAdd: () -> Unit,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
    showDelete: Boolean = false,
) {
    val enabled by remember { derivedStateOf { quantity > 0 } }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        @Suppress("MagicNumber")
        val alpha = if (enabled) 1f else 0.3f

        val decrement = Modifier.clickable(enabled = enabled) { onRemove() }

        if (showDelete) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error.copy(alpha = alpha),
                modifier = Modifier.thenIf(enabled) { decrement },
            )
        } else {
            Icon(
                imageVector = Icons.Default.RemoveCircle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary.copy(alpha = alpha),
                modifier = Modifier.thenIf(enabled) { decrement },
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "$quantity",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            imageVector = Icons.Default.AddCircle,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { onAdd() },
        )
    }
}

@Preview
@Composable
private fun QuantityControlPreview() {
    GoCartTheme {
        QuantityControl(onUpdate = {}, showDelete = true)
    }
}
