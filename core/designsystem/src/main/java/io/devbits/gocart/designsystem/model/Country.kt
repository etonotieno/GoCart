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

import io.devbits.gocart.resources.R

data class Country(
    val name: String,
    val code: Int,
    val flag: Int,
)

val defaultCountries: List<Country> = listOf(
    Country(name = "Kenya", code = 254, flag = R.drawable.ic_outlined_kenyaflag),
    Country(name = "Uganda", code = 256, flag = R.drawable.ic_outlined_kenyaflag),
    Country(name = "Tanzania", code = 255, flag = R.drawable.ic_outlined_kenyaflag),
    Country(name = "Rwanda", code = 250, flag = R.drawable.ic_outlined_kenyaflag),
    Country(name = "Cote d'voire", code = 225, flag = R.drawable.ic_outlined_kenyaflag),
    Country(name = "Ghana", code = 233, flag = R.drawable.ic_outlined_kenyaflag),
    Country(name = "Nigeria", code = 234, flag = R.drawable.ic_outlined_kenyaflag),
)
