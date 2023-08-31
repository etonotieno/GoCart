package io.devbits.gocart.favorites.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class FavoritesViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow("Favorites")
    val uiState: StateFlow<String> get() = _uiState
}
