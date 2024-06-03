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

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.devbits.gocart.designsystem.theme.GoCartTheme

private const val ANIMATION_DURATION = 400

@Immutable
class IndicatorColors(
    private val color: Color,
    private val selectedColor: Color,
) {

    @Composable
    internal fun containerColor(selected: Boolean): State<Color> {
        return rememberUpdatedState(if (selected) selectedColor else color)
    }

    @Composable
    internal fun borderColor(): State<Color> {
        return rememberUpdatedState(selectedColor)
    }
}

object IndicatorDefaults {
    val IndicatorSize = 8.dp
    val BorderSize = 1.dp
    val Spacing = 8.dp

    @Composable
    fun indicatorColors(
        color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
        selectedColor: Color = MaterialTheme.colorScheme.secondary,
    ): IndicatorColors = IndicatorColors(
        color = color,
        selectedColor = selectedColor,
    )
}

@Composable
fun Indicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    indicatorSize: Dp = IndicatorDefaults.IndicatorSize,
    colors: IndicatorColors = IndicatorDefaults.indicatorColors(),
    border: Boolean = false,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(IndicatorDefaults.Spacing),
    ) {
        repeat(pageCount) { page ->
            val isSelected = currentPage == page
            val containerColor = colors.containerColor(isSelected).value

            val indicatorColor by animateColorAsState(
                targetValue = containerColor,
                animationSpec = tween(durationMillis = ANIMATION_DURATION),
                label = "anim:indicatorColor",
            )

            val borderModifier = if (border) {
                Modifier.border(
                    width = IndicatorDefaults.BorderSize,
                    color = colors.borderColor().value,
                    shape = CircleShape,
                )
            } else {
                Modifier
            }

            Box(
                modifier = Modifier
                    .size(indicatorSize)
                    .clip(CircleShape)
                    .then(borderModifier)
                    .background(indicatorColor),
            )
        }
    }
}

@Preview
@Composable
private fun IndicatorPreview() {
    GoCartTheme {
        Indicator(pageCount = 3, currentPage = 1, border = true)
    }
}
