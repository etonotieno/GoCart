package io.devbits.gocart.address.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class AddressViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow("My Addresses")
    val uiState: StateFlow<String> get() = _uiState
}
