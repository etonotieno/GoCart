package io.devbits.gocart.designsystem.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle

/**
 * [Text] component that displays a styled pair.
 *
 * @param pair The pair of text to display. The style is only applied to the second value of pair.
 * @param style The span style to apply
 */
@Composable
fun GCStyledTextPair(
    pair: Pair<String, String>,
    style: SpanStyle,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
) {
    val finalText = buildAnnotatedString {
        append(pair.first)
        withStyle(style) {
            append(pair.second)
        }
    }

    Text(
        text = finalText,
        textAlign = textAlign,
        style = textStyle,
        modifier = modifier,
    )
}
