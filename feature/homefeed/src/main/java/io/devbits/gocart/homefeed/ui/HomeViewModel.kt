package io.devbits.gocart.homefeed.ui

import androidx.lifecycle.ViewModel
import io.devbits.gocart.core.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow

class HomeViewModel(
    // TODO: Remove dependency on ":core:datastore"
    private val preferences: UserPreferences,
) : ViewModel() {

    val isLoggedIn: Flow<Boolean>
        get() = preferences.isAuthenticated()

}
