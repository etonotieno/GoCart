package io.devbits.gocart.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun GCIconBackground(
    color: Color,
    modifier: Modifier = Modifier,
    content: @Composable (BoxScope.() -> Unit),
) {
    Box(
        modifier = modifier.background(color),
        contentAlignment = Alignment.Center,
        content = content,
    )
}
