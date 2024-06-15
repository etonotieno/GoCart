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
package io.devbit.gocart.orders.ui

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.devbit.gocart.orders.ui.preview.sampleRecentOrders
import io.devbits.gocart.designsystem.theme.cancelled
import io.devbits.gocart.designsystem.theme.delivered
import io.devbits.gocart.designsystem.theme.pending
import io.devbits.gocart.designsystem.theme.placed
import io.devbits.gocart.designsystem.theme.ready_to_pickup
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class OrdersViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<OrdersUiState>(Loading)
    val uiState: StateFlow<OrdersUiState> get() = _uiState

    init {
        viewModelScope.launch {
            // Simulate a network call
            delay(4.seconds)
            _uiState.value = Empty
            delay(4.seconds)
            _uiState.value = Success(sampleRecentOrders)
        }
    }
}

sealed interface OrdersUiState

data object Loading : OrdersUiState

data object Empty : OrdersUiState

data class Success(val orders: List<UiOrderItem>) : OrdersUiState

data class UiOrderItem(
    val id: String,
    val number: String,
    val price: String,
    val date: String,
    val status: OrderStatus,
)

enum class OrderStatus(val color: Color, val text: String) {
    Placed(placed, "Placed"),
    Pending(pending, "Pending"),
    ReadyToPickup(ready_to_pickup, "Ready to Pickup"),
    Delivered(delivered, "Delivered"),
    Cancelled(cancelled, "Cancelled"),
}
