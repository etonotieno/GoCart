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
package io.devbits.gocart.authentication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import io.devbits.gocart.designsystem.component.AuthButton
import io.devbits.gocart.designsystem.component.FacebookSignupButton
import io.devbits.gocart.designsystem.component.GoogleSignupButton
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.designsystem.theme.go_cart_orange_yellow
import io.devbits.gocart.resources.R as resourcesR

@Composable
fun AuthenticationRoute(
    onExploreApp: () -> Unit,
    onGoogleSignup: () -> Unit,
    onFacebookSignup: () -> Unit,
    onSignup: () -> Unit,
    onLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthenticationViewModel = hiltViewModel(),
) {
    AuthenticationScreen(
        modifier = modifier,
        onExploreApp = onExploreApp,
        onGoogleSignup = onGoogleSignup,
        onFacebookSignup = onFacebookSignup,
        onSignup = onSignup,
        onLogin = onLogin,
    )
}

@Composable
fun AuthenticationScreen(
    onExploreApp: () -> Unit,
    onGoogleSignup: () -> Unit,
    onFacebookSignup: () -> Unit,
    onSignup: () -> Unit,
    onLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = resourcesR.drawable.ic_signup_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .padding(top = 72.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Welcome to",
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.size(24.dp))

            Image(
                painter = painterResource(id = resourcesR.drawable.ic_go_cart_horizontal_white),
                contentDescription = null,
                modifier = Modifier.height(32.dp),
            )

            Spacer(modifier = Modifier.size(56.dp))

            GoogleSignupButton(onClick = onGoogleSignup)

            Spacer(modifier = Modifier.size(16.dp))

            FacebookSignupButton(onClick = onFacebookSignup)

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = "OR",
                color = MaterialTheme.colorScheme.inverseOnSurface,
                style = MaterialTheme.typography.headlineSmall,
            )

            Spacer(modifier = Modifier.size(16.dp))

            AuthButton(text = "Sign Up", onClick = onSignup)

            Spacer(modifier = Modifier.size(40.dp))

            HaveAccountText(
                onLogin = onLogin,
                textColor = MaterialTheme.colorScheme.inverseOnSurface,
                loginStyle = SpanStyle(
                    color = go_cart_orange_yellow,
                    fontWeight = FontWeight.Bold,
                ),
            )

            Spacer(modifier = Modifier.size(16.dp))

            Row {
                Text(
                    text = "Or simply, ",
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                )
                val explore = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = go_cart_orange_yellow,
                            fontWeight = FontWeight.Bold,
                        ),
                    ) {
                        append("Explore app")
                    }
                }
                Text(
                    text = explore,
                    modifier = Modifier.clickable { onExploreApp() },
                )
            }
        }
    }
}

@Composable
fun HaveAccountText(
    onLogin: () -> Unit,
    textColor: Color,
    loginStyle: SpanStyle,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Text(
            text = "Already heave an account, ",
            color = textColor,
        )

        val login = buildAnnotatedString {
            withStyle(loginStyle) {
                append("Login")
            }
        }

        Text(
            text = login,
            modifier = Modifier.clickable { onLogin() },
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AuthenticationScreenPreview() {
    GoCartTheme {
        AuthenticationScreen(
            onExploreApp = {},
            onGoogleSignup = {},
            onFacebookSignup = {},
            onSignup = {},
            onLogin = {},
        )
    }
}
