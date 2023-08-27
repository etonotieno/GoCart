package io.devbits.gocart.address.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddressesScreen(modifier: Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "My Addresses")
    }
}
