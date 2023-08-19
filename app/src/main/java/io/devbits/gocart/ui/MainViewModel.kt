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

    private val _showSplashScreen = mutableStateOf(true)
    val showSplashScreen: State<Boolean> = _showSplashScreen

    private val _startDestination = mutableStateOf(onboardingRoute)
    val startDestination: State<String> = _startDestination

    init {
        initializeState(preferences)
    }

    /**
     * Combine the onboarded and authenticated Flows to determine the correct start destination
     *  for the NavHost
     */
    private fun initializeState(preferences: UserPreferences) {
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
            // Without this delay, the onboarding &/or the authentication screens will flicker
            delay(300)
            _showSplashScreen.value = false
        }.launchIn(viewModelScope)
    }

}