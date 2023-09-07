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
package io.devbits.gocart.authentication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.devbits.gocart.core.datastore.UserPreferences
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val preferences: UserPreferences,
) : ViewModel() {

    private val _uiState = MutableStateFlow("Auth Screen")
    val uiState: StateFlow<String> get() = _uiState

    fun setGuestUser(guest: Boolean) {
        viewModelScope.launch {
            preferences.setGuestUser(guest)
        }
    }

    fun setAuthenticated(authenticated: Boolean) {
        viewModelScope.launch {
            preferences.setAuthenticated(authenticated)
        }
    }
}
