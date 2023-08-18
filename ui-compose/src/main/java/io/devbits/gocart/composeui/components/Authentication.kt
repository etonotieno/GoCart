package io.devbits.gocart.composeui.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.devbits.gocart.composeui.theme.GoCartTheme
import io.devbits.gocart.composeui.theme.facebook_blue
import io.devbits.gocart.composeui.theme.google_red
import io.devbits.gocart.resources.R as resourcesR

@Composable
fun AuthButton(
    text: String,
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int? = null,
    containerColor: Color? = null,
    onClick: () -> Unit,
) {
    val colors = if (containerColor != null)
        ButtonDefaults.buttonColors(containerColor = containerColor)
    else
        ButtonDefaults.buttonColors()

    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        onClick = onClick,
        colors = colors,
        shape = RoundedCornerShape(50.dp)
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
        onClick = onClick,
        modifier = modifier,
    )
}

@Preview
@Composable
fun AuthButtonsPreview() {
    GoCartTheme {
        Column {
            GoogleSignupButton(onClick = {})
            Spacer(modifier = Modifier.size(24.dp))
            FacebookSignupButton(onClick = {})
        }
    }
}