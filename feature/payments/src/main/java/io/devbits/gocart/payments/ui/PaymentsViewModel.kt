package io.devbits.gocart.payments.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class PaymentsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow("Digital Payments")
    val uiState: StateFlow<String> get() = _uiState
}
