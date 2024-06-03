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
package io.devbits.gocart.designsystem.model

import io.devbits.gocart.resources.R as resourcesR

enum class ProductCategory(
    val label: String,
    val image: Int,
    val quantity: Int,
    val limit: Int,
) {
    Fruits(
        label = "Fruits",
        image = resourcesR.drawable.ic_product_category_fruits,
        quantity = 120,
        limit = 5,
    ),
    Vegetables(
        label = "Vegetables",
        image = resourcesR.drawable.ic_product_category_vegetables,
        quantity = 120,
        limit = 5,
    ),
    Drinks(
        label = "Drinks",
        image = resourcesR.drawable.ic_product_category_drinks,
        quantity = 120,
        limit = 5,
    ),
    Meat(
        label = "Meat",
        image = resourcesR.drawable.ic_product_category_meat,
        quantity = 120,
        limit = 5,
    ),
    Spreads(
        label = "Spreads",
        image = resourcesR.drawable.ic_product_category_spreads,
        quantity = 120,
        limit = 5,
    ),
    Condiments(
        label = "Condiments",
        image = resourcesR.drawable.ic_product_category_condiments,
        quantity = 120,
        limit = 5,
    ),
    GrainBakery(
        label = "Grain & Bakery",
        image = resourcesR.drawable.ic_product_category_grains_bakery,
        quantity = 120,
        limit = 5,
    ),
    Cleaners(
        label = "Cleaners",
        image = resourcesR.drawable.ic_product_category_cleaners,
        quantity = 120,
        limit = 5,
    ),
    Other(
        label = "Other",
        image = resourcesR.drawable.ic_placeholder,
        quantity = 120,
        limit = 4,
    ),
}
