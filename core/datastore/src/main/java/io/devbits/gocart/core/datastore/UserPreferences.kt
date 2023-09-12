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
package io.devbits.gocart.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.devbits.gocart.core.datastore.model.AppTheme
import io.devbits.gocart.core.datastore.model.getThemeFromKey
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {

    suspend fun setOnboarded(): Preferences =
        dataStore.edit { it[onboardingKey] = true }

    fun isOnboarded(): Flow<Boolean> =
        dataStore.data.map { it[onboardingKey] ?: false }

    suspend fun setAuthenticated(status: Boolean): Preferences =
        dataStore.edit { it[authKey] = status }

    fun isAuthenticated(): Flow<Boolean> =
        dataStore.data.map { it[authKey] ?: false }

    fun isGuestUser(): Flow<Boolean> =
        dataStore.data.map { it[guestUserKey] ?: false }

    suspend fun setGuestUser(guest: Boolean) =
        dataStore.edit { it[guestUserKey] = guest }

    fun getAppTheme(): Flow<AppTheme> = dataStore.data.map {
        getThemeFromKey(it[appThemeKey])
    }

    suspend fun setAppTheme(theme: AppTheme) {
        dataStore.edit { it[appThemeKey] = theme.name.lowercase() }
    }

    fun useDynamicTheme(): Flow<Boolean> =
        dataStore.data.map { it[dynamicThemeKey] ?: false }

    suspend fun setDynamicTheme(dynamic: Boolean) {
        dataStore.edit { it[dynamicThemeKey] = dynamic }
    }

    companion object {
        private val onboardingKey = booleanPreferencesKey("onboarding")
        private val authKey = booleanPreferencesKey("authentication")
        private val guestUserKey = booleanPreferencesKey("user:guest")
        private val appThemeKey = stringPreferencesKey("app_theme")
        private val dynamicThemeKey = booleanPreferencesKey("dynamic_theme")
    }
}
