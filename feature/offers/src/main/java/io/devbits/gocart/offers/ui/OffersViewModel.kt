package io.devbits.gocart.offers.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class OffersViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow("Special Offers")
    val uiState: StateFlow<String> get() = _uiState
}
