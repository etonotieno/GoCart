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
package io.devbits.gocart.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.devbits.gocart.designsystem.component.sampleProducts
import io.devbits.gocart.favorites.ui.state.Empty
import io.devbits.gocart.favorites.ui.state.FavoritesUiState
import io.devbits.gocart.favorites.ui.state.Loading
import io.devbits.gocart.favorites.ui.state.Success
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class FavoritesViewModel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<FavoritesUiState> = MutableStateFlow(Loading)
    val uiState: StateFlow<FavoritesUiState> get() = _uiState

    init {
        viewModelScope.launch {
            // Simulate a network call
            delay(4.seconds)
            _uiState.value = Success(sampleProducts.shuffled().take(PRODUCT_SIZE))
        }
    }

    fun onClearFavorites() {
        _uiState.value = Empty
    }

    companion object {
        private const val PRODUCT_SIZE = 5
    }
}
