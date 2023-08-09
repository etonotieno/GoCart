plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    compileSdk = 33
    namespace = "io.devbits.core"

    defaultConfig {
        minSdk = 23
        targetSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    api(platform("androidx.compose:compose-bom:2023.06.01"))
    api("androidx.compose.ui:ui")
    api("androidx.compose.ui:ui-graphics")
    api("androidx.compose.ui:ui-tooling-preview")
    api("androidx.compose.material3:material3")

    androidTestApi(platform("androidx.compose:compose-bom:2023.06.01"))
    androidTestApi("androidx.compose.ui:ui-test-junit4")
    debugApi("androidx.compose.ui:ui-tooling")
    debugApi("androidx.compose.ui:ui-test-manifest")

    api("androidx.datastore:datastore-preferences:1.0.0")

    api("com.google.android.material:material:1.9.0")

    api("androidx.recyclerview:recyclerview:1.3.1")

    api("androidx.activity:activity-ktx:1.7.2")

    api("androidx.room:room-runtime:2.5.2")
    api("androidx.room:room-ktx:2.5.2")
    ksp("androidx.room:room-compiler:2.5.2")

    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0")

    api("com.squareup.okhttp3:logging-interceptor:4.11.0")
    api("com.squareup.okhttp3:okhttp:4.11.0")

    api("io.coil-kt:coil:2.4.0")

    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    api("androidx.core:core-ktx:1.10.1")

    api("androidx.constraintlayout:constraintlayout:2.1.4")

    api("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
}