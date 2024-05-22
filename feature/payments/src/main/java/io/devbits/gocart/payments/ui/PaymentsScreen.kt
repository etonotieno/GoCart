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
package io.devbits.gocart.payments.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.devbits.gocart.designsystem.theme.GoCartSurface
import io.devbits.gocart.designsystem.theme.GoCartTheme

@Composable
fun PaymentsScreen(modifier: Modifier = Modifier, viewModel: PaymentsViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    PaymentsScreen(modifier = modifier, state = state)
}

@Composable
fun PaymentsScreen(state: String, modifier: Modifier = Modifier) {
    GoCartSurface(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = state)
        }
    }
}

@Preview
@Composable
private fun PaymentsScreenPreview() {
    GoCartTheme {
        PaymentsScreen(state = "")
    }
}
