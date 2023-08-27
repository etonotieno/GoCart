package io.devbits.gocart.authentication.ui.signup

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.PersonOutline
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.util.PatternsCompat
import io.devbits.gocart.authentication.R
import io.devbits.gocart.authentication.ui.HaveAccountText
import io.devbits.gocart.composeui.theme.GoCartTheme
import java.util.regex.Pattern
import io.devbits.gocart.resources.R as ResourcesR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onLogin: () -> Unit,
    onSignUp: () -> Unit,
    onBack: ()-> Unit,
) {
    var fullName by remember { mutableStateOf("") }
    var fullNameError by remember { mutableStateOf(false) }

    var phone by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf(false) }

    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }

    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf(false) }

    var agreesToTerms by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Create an Account") },
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
            TextField(
                value = fullName,
                onValueChange = {
                    fullName = it
                    val name = fullName.split(" ")
                    fullNameError = name.size <= 1
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Kevin Kaur")
                },
                label = {
                    Text(text = "Full Name")
                },
                leadingIcon = {
                    Icon(Icons.Outlined.PersonOutline, null)
                },
                supportingText = {
                    if (fullNameError) {
                        Text(text = "Enter your full name")
                    }
                },
                isError = fullNameError,
            )

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
                            painter = painterResource(id = ResourcesR.drawable.ic_outlined_kenyaflag),
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
                value = email,
                onValueChange = {
                    email = it
                    emailError = !Pattern.matches(PatternsCompat.EMAIL_ADDRESS.pattern(), it)
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "kevinkaur60@gmail.com")
                },
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

            TextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    confirmPasswordError = password != confirmPassword
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Confirm your password")
                },
                label = {
                    Text(text = "Confirm Password")
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Lock, null)
                },
                supportingText = {
                    if (confirmPasswordError) {
                        Text(text = "Passwords do not match")
                    }
                },
                isError = confirmPasswordError,
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = agreesToTerms, onCheckedChange = { agreesToTerms = it })

                Text(text = "I agree to the Terms and Conditions")
            }

            HaveAccountText(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onLogin = onLogin,
                textColor = MaterialTheme.colorScheme.onSurface,
                loginStyle = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                ),
            )

            Button(
                onClick = onSignUp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
            ) {
                Text(text = "SIGN UP")
            }
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    GoCartTheme {
        SignUpScreen(
            onLogin = {},
            onSignUp = {},
            onBack = {},
        )
    }
}
