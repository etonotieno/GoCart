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

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.devbits.gocart.designsystem.theme.GoCartTheme
import io.devbits.gocart.designsystem.theme.facebook_blue
import io.devbits.gocart.designsystem.theme.go_cart_eggshell
import io.devbits.gocart.designsystem.theme.google_red
import io.devbits.gocart.resources.R as resourcesR

@Composable
fun AuthButton(
    text: String,
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int? = null,
    containerColor: Color? = null,
    contentColor: Color? = null,
    onClick: () -> Unit,
) {
    val colors = ButtonDefaults.buttonColors(
        containerColor = containerColor ?: MaterialTheme.colorScheme.primary,
        contentColor = contentColor ?: MaterialTheme.colorScheme.onPrimary,
    )

    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        onClick = onClick,
        colors = colors,
        shape = RoundedCornerShape(50.dp),
    ) {
        if (icon != null) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.size(8.dp))
        }

        Text(text = text.uppercase())
    }
}

@Composable
fun GoogleSignupButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    AuthButton(
        text = "SIGN UP WITH GOOGLE",
        icon = resourcesR.drawable.ic_google_white,
        containerColor = google_red,
        contentColor = go_cart_eggshell,
        onClick = onClick,
        modifier = modifier,
    )
}

@Composable
fun FacebookSignupButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    AuthButton(
        text = "SIGN UP WITH FACEBOOK",
        icon = resourcesR.drawable.ic_outline_facebook,
        containerColor = facebook_blue,
        contentColor = go_cart_eggshell,
        onClick = onClick,
        modifier = modifier,
    )
}

@ThemePreviews
@Composable
private fun AuthButtonsPreview() {
    GoCartTheme {
        Column {
            GoogleSignupButton(onClick = {})
            Spacer(modifier = Modifier.size(24.dp))
            FacebookSignupButton(onClick = {})
            Spacer(modifier = Modifier.size(24.dp))
            AuthButton(text = "Sign Up", onClick = {})
        }
    }
}
