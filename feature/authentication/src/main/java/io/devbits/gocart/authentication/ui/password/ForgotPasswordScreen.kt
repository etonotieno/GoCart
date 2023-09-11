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
package io.devbits.gocart.authentication.ui.password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.util.PatternsCompat
import io.devbits.gocart.authentication.R
import io.devbits.gocart.designsystem.theme.GoCartTheme
import java.util.regex.Pattern

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    onBack: () -> Unit,
    onSend: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Forgot Password?") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.cd_navigate_back),
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Please enter your email address associated with your account and we will instructions to reset your password via email.")

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = !Pattern.matches(PatternsCompat.EMAIL_ADDRESS.pattern(), it)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Email")
                },
                leadingIcon = {
                    Icon(Icons.Outlined.MailOutline, null)
                },
                supportingText = {
                    if (emailError) {
                        Text(text = "Email does not exist, try a valid email")
                    }
                },
                isError = emailError,
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onSend, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Send")
            }
        }
    }
}

@Preview
@Composable
private fun ForgotPasswordScreenPreview() {
    GoCartTheme {
        ForgotPasswordScreen(onBack = {}, onSend = {})
    }
}
