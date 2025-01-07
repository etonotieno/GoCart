/*
 * Copyright 2025 Eton Otieno
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
package io.devbits.gocart.services.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.devbits.gocart.designsystem.component.IconContainer
import io.devbits.gocart.designsystem.theme.GoCartTheme

@Composable
fun ChatRoute(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ChatScreen(
        onBack = onBack,
        modifier = modifier,
    )
}

@Composable
fun ChatScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val chatHasStarted by remember { mutableStateOf(true) }

    Scaffold(
        modifier = modifier,
        topBar = {
            ChatAppBar(chatHasStarted = chatHasStarted, onExitChat = {}, onBack = onBack)
        },
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp),
        ) {
        }
    }
}

@Composable
private fun ChatAppBar(
    chatHasStarted: Boolean,
    onBack: () -> Unit,
    onExitChat: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
    ) {
        IconButton(onClick = onBack) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 16.dp),
        ) {
            IconContainer(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
            ) {
                Text(text = "G", color = MaterialTheme.colorScheme.surfaceVariant)
            }

            Spacer(Modifier.size(8.dp))

            Column {
                Text(text = "GoCart chat support", fontWeight = FontWeight.Bold)

                if (chatHasStarted) {
                    Text(
                        text = "Type \"Hello\" to begin",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }

        TextButton(onClick = onExitChat, contentPadding = PaddingValues(horizontal = 8.dp)) {
            Text(
                text = "Exit chat",
                color = MaterialTheme.colorScheme.primary,
            )
        }

        Spacer(Modifier.size(16.dp))
    }
}

@PreviewLightDark
@Composable
private fun ChatScreenPreview() {
    GoCartTheme {
        ChatScreen(onBack = {})
    }
}
