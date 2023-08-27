package io.devbits.gocart.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(
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

    // TODO Represent this is a User state object
    fun isGuestUser(): Flow<Boolean> =
        dataStore.data.map { it[guestUserKey] ?: false }

    suspend fun setGuestUser(guest: Boolean) =
        dataStore.edit { it[guestUserKey] = guest }

    companion object {
        private val onboardingKey = booleanPreferencesKey("onboarding")
        private val authKey = booleanPreferencesKey("authentication")
        private val guestUserKey = booleanPreferencesKey("user:guest")
    }
}
