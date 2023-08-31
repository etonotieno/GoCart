package io.devbits.gocart.authentication.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class AuthenticationViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow("Auth Screen")
    val uiState: StateFlow<String> get() = _uiState
}
