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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.devbits.gocart.designsystem.theme.GoCartTheme

@Composable
fun GCAlertDialog(
    modifier: Modifier = Modifier,
    title: @Composable (() -> Unit)?,
    text: @Composable (() -> Unit)?,
    onDismiss: () -> Unit = {},
    dismissButton: @Composable (() -> Unit)?,
    confirmButton: @Composable () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = confirmButton,
        dismissButton = dismissButton,
        title = title,
        text = text,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GCAlertDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    AlertDialog(onDismissRequest = onDismiss, modifier = modifier) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            color = AlertDialogDefaults.containerColor,
            tonalElevation = AlertDialogDefaults.TonalElevation,
            content = content,
        )
    }
}

@Composable
fun SuccessDialog(
    text: String,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    confirmButton: @Composable (() -> Unit)? = null,
) {
    GCAlertDialog(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp),
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier.size(48.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = text)

            Spacer(modifier = Modifier.height(40.dp))

            if (confirmButton != null) {
                confirmButton()
            } else {
                Button(onClick = onConfirm) {
                    Text(text = "OK")
                }
            }
        }
    }
}

@ThemePreviews
@Composable
private fun SuccessDialogPreview() {
    GoCartTheme {
        SuccessDialog(text = "Login Successful", onConfirm = {})
    }
}

@ThemePreviews
@Composable
private fun GCAlertDialogPreview() {
    GoCartTheme {
        GCAlertDialog(
            confirmButton = {
                Button(onClick = {}) {
                    Text(text = "Create")
                }
            },
            dismissButton = {
                TextButton(onClick = {}) {
                    Text(text = "Cancel")
                }
            },
            title = {
                Text(text = "Create Account")
            },
            text = {
                Text(text = "Confirm that you want to create an account with GoCart")
            },
        )
    }
}
