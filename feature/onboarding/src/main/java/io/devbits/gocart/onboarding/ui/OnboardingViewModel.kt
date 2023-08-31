package io.devbits.gocart.onboarding.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class OnboardingViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow("Onboarding")
    val uiState: StateFlow<String> get() = _uiState
}
