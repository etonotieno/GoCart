package io.devbits.gocart.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devbits.gocart.authentication.navigation.authenticationRoute
import io.devbits.gocart.core.data.UserPreferences
import io.devbits.gocart.navigation.homeRoute
import io.devbits.gocart.onboarding.navigation.onboardingRoute
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(
    preferences: UserPreferences
) : ViewModel() {

    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _startDestination = mutableStateOf(onboardingRoute)
    val startDestination: State<String> = _startDestination

    init {
        combine(
            preferences.isOnboarded(),
            preferences.isAuthenticated()
        ) { isOnboarded: Boolean, isAuthenticated ->
            if (isOnboarded) {
                if (isAuthenticated) {
                    homeRoute
                } else {
                    authenticationRoute
                }
            } else {
                onboardingRoute
            }
        }.onEach { startDestination ->
            _startDestination.value = startDestination
            // Without this delay, the onboarding &/or the authentication screens will show for a momentum.
            delay(300)
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }

}