/*
 * Copyright 2024 Eton Otieno
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
package io.devbits.gocart.product.filter.ui

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.devbits.gocart.designsystem.model.ProductCategory
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class ProductFilterViewModel @Inject constructor() : ViewModel() {

    val produce: StateFlow<List<String>> = MutableStateFlow(produceList)

    fun onApplyFilter(
        selectedProduce: SnapshotStateList<String>,
        activeRangeStart: Float,
        activeRangeEnd: Float,
        selectedCategories: SnapshotStateList<ProductCategory>,
    ) {
    }
}
