package io.devbits.gocart.services.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class ServicesViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow("Services")
    val uiState: StateFlow<String> get() = _uiState
}
