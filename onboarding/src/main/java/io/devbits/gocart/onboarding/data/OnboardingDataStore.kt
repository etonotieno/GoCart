package io.devbits.gocart.onboarding.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OnboardingDataStore(private val dataStore: DataStore<Preferences>) {

    suspend fun setIsOnboarding(mode: Boolean): Preferences = dataStore.edit { preferences ->
        preferences[KEY_MODE] = mode
    }

    fun isOnboarding(defaultMode: Boolean): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[KEY_MODE] ?: defaultMode
    }

    companion object {
        private val KEY_MODE = booleanPreferencesKey("onboarding")
    }
}