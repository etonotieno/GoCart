package io.devbits.gocart.authentication.ui.password

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.devbits.gocart.authentication.R
import io.devbits.gocart.designsystem.component.GCPasswordTextField
import io.devbits.gocart.designsystem.component.GoCartAlert
import io.devbits.gocart.designsystem.component.SuccessDialog
import io.devbits.gocart.designsystem.theme.GoCartTheme

@Composable
fun UpdatePasswordRoute(
    onBack: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdatePasswordViewModel = hiltViewModel(),
) {
    UpdatePasswordScreen(
        onBack = onBack,
        onSave = {
            viewModel.setAuthenticated()
            onSave()
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun UpdatePasswordScreen(
    onBack: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboard = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }

    var confirmPassword by remember { mutableStateOf("") }
    val confirmPasswordError by remember { derivedStateOf { password != confirmPassword } }

    val samePassword by remember { derivedStateOf { password == "12345678" } }

    var showSuccessDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Update your password") },
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
            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Please enter your new password")

            AnimatedVisibility(visible = samePassword) {
                GoCartAlert(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    icon = Icons.Outlined.Warning,
                    text = "Your new password must be different from previously used passwords.",
                    iconColor = MaterialTheme.colorScheme.error,
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            GCPasswordTextField(
                password = password,
                onValueChange = {
                    password = it
                    passwordError = password.length < 8
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) },
                ),
                modifier = Modifier.fillMaxWidth(),
                label = "New Password",
                errorText = "Must be at least 8 characters long",
                isError = passwordError,
            )

            GCPasswordTextField(
                password = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) },
                ),
                modifier = Modifier.fillMaxWidth(),
                label = "Confirm Password",
                errorText = "Passwords do not match",
                isError = confirmPasswordError,
            )

            Spacer(modifier = Modifier.height(96.dp))

            Button(
                onClick = {
                    keyboard?.hide()
                    showSuccessDialog = !samePassword && !confirmPasswordError
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Text(text = "Save")
            }
        }
    }

    if (showSuccessDialog) {
        SuccessDialog(text = "Password saved successfully", onConfirm = onSave)
    }
}

@Preview
@Composable
private fun UpdatePasswordPreview() {
    GoCartTheme {
        UpdatePasswordScreen(onBack = {}, onSave = {})
    }
}
