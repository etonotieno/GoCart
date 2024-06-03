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
package io.devbits.gocart.product.categories.detail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.devbits.gocart.designsystem.component.sampleProducts
import io.devbits.gocart.designsystem.model.Product
import io.devbits.gocart.designsystem.model.ProductCategory
import io.devbits.gocart.product.categories.detail.navigation.categoryArg
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class ProductCategoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val products: StateFlow<List<Product>> = MutableStateFlow(sampleProducts)

    private val categoryName: String = checkNotNull(savedStateHandle[categoryArg])
    val category: ProductCategory = ProductCategory.valueOf(categoryName)
}
