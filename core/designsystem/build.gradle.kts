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
plugins {
    id("gocart.android.library")
    id("gocart.kotlin.android")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "io.devbits.gocart.designsystem"
}

dependencies {
    implementation(projects.core.model)
    api(projects.core.resources)

    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material.iconsExtended)

    api(libs.androidx.core.ktx)

    api(libs.androidx.activity.compose)

    api(libs.androidx.lifecycle.runtime.compose)

    api(libs.androidx.navigation.compose)

    api(libs.coil.compose)

    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.testManifest)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
