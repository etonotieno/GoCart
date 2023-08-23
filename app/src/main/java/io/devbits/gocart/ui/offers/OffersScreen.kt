package io.devbits.gocart.ui.offers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OffersScreen(modifier: Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Special Offers")
    }
}
