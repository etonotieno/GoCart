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
    `kotlin-dsl`
    alias(libs.plugins.spotless)
}

kotlin {
    jvmToolchain(17)
}

spotless {
    kotlin {
        target("src/**/*.kt")
        ktlint(libs.versions.ktlint.get())
        licenseHeaderFile(rootProject.file("../spotless/copyright.kt"))
    }

    kotlinGradle {
        target("*.kts")
        ktlint(libs.versions.ktlint.get())
        licenseHeaderFile(rootProject.file("../spotless/copyright.kts"), "(^(?![\\/ ]\\**).*$)")
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("root") {
            id = "gocart.root"
            implementationClass = "RootConventionPlugin"
        }
        register("androidApplication") {
            id = "gocart.android.application"
            implementationClass = "AndroidAppConventionPlugin"
        }
        register("kotlinAndroid") {
            id = "gocart.kotlin.android"
            implementationClass = "KotlinAndroidConventionPlugin"
        }
    }
}
