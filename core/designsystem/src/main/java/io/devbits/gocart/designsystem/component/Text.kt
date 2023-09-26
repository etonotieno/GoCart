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
        append(" ")
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
