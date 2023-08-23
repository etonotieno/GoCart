package io.devbits.gocart.ui.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FavoritesScreen(modifier: Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Favorites")
    }
}
