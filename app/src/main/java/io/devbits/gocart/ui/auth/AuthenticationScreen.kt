package io.devbits.gocart.ui.auth

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.devbits.gocart.composeui.components.AuthButton
import io.devbits.gocart.composeui.components.FacebookSignupButton
import io.devbits.gocart.composeui.components.GoogleSignupButton
import io.devbits.gocart.composeui.theme.GoCartTheme
import io.devbits.gocart.composeui.theme.go_cart_orange_yellow
import io.devbits.gocart.resources.R as resourcesR

@Composable
fun AuthenticationRoute(
    onExploreApp: () -> Unit,
    onGoogleSignup: () -> Unit,
    onFacebookSignup: () -> Unit,
    onSignup: () -> Unit,
    onLogin: () -> Unit,
    modifier: Modifier,
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
    modifier: Modifier = Modifier,
    onExploreApp: () -> Unit,
    onGoogleSignup: () -> Unit,
    onFacebookSignup: () -> Unit,
    onSignup: () -> Unit,
    onLogin: () -> Unit,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = resourcesR.drawable.ic_signup_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = modifier
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
                modifier = Modifier.height(32.dp)
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

            Row {
                Text(
                    text = "Already heave an account, ",
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                )

                val login = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = go_cart_orange_yellow,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Login")
                    }
                }
                Text(
                    text = login,
                    modifier = Modifier.clickable { onLogin() },
                )
            }

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
                            fontWeight = FontWeight.Bold
                        )
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AuthenticationScreenPreview() {
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