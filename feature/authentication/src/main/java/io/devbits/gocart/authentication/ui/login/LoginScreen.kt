package io.devbits.gocart.authentication.ui.login

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.devbits.gocart.authentication.R
import io.devbits.gocart.designsystem.theme.GoCartTheme
import java.util.regex.Pattern

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onLogin: () -> Unit,
    navigateToSignUp: () -> Unit,
    onForgotPassword: () -> Unit,
) {
    var phone by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }

    var rememberMe by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Welcome Back") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.cd_navigate_back),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Proceed to login")

            TextField(
                value = phone,
                onValueChange = {
                    phone = it
                    phoneError = !Pattern.matches(Patterns.PHONE.pattern(), it)
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Phone Number")
                },
                leadingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = io.devbits.gocart.resources.R.drawable.ic_outlined_kenyaflag),
                            contentDescription = null,
                        )
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                },
                supportingText = {
                    if (phoneError) {
                        Text(text = "Invalid phone number, try again")
                    }
                },
                isError = phoneError,
            )

            TextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = it.length < 8
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Enter your password")
                },
                label = {
                    Text(text = "Password")
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Lock, null)
                },
                supportingText = {
                    if (passwordError) {
                        Text(text = "This password doesn't look right, try again")
                    }
                },
                isError = passwordError,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable(onClick = { rememberMe = !rememberMe })
                ) {
                    Checkbox(checked = rememberMe, onCheckedChange = { rememberMe = it })

                    Text(text = "Remember Me")
                }

                Text(
                    text = "Forgot Password",
                    modifier = Modifier.clickable(onClick = onForgotPassword)
                )
            }

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Don't have an account, ")

                val signUp = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline,
                        )
                    ) {
                        append("Sign Up")
                    }
                }

                Text(
                    text = signUp,
                    modifier = Modifier.clickable { navigateToSignUp() },
                )
            }

            Button(
                onClick = onLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
            ) {
                Text(text = "Login")
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    GoCartTheme {
        LoginScreen(
            onBack = {},
            onLogin = {},
            navigateToSignUp = {},
            onForgotPassword = {}
        )
    }
}
