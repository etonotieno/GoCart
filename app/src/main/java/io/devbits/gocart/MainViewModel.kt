/*
 * Copyright 2023 Eton Otieno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.devbits.gocart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devbits.gocart.authentication.navigation.authenticationRoute
import io.devbits.gocart.core.datastore.UserPreferences
import io.devbits.gocart.homefeed.navigation.homeRoute
import io.devbits.gocart.onboarding.navigation.onboardingRoute
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(
    preferences: UserPreferences,
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
            preferences.isAuthenticated(),
            preferences.isGuestUser(),
        ) { isOnboarded: Boolean, isAuthenticated, isGuestUser ->
            if (isOnboarded) {
                if (isAuthenticated || isGuestUser) {
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
            delay(DELAY)
            _showSplashScreen.value = false
        }.launchIn(viewModelScope)
    }

    companion object {
        private const val DELAY: Long = 300
    }
}
