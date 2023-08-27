package io.devbits.gocart.homefeed.ui

import androidx.lifecycle.ViewModel
import io.devbits.gocart.core.data.UserPreferences
import kotlinx.coroutines.flow.Flow

class HomeViewModel(
    private val preferences: UserPreferences,
) : ViewModel() {

    val isLoggedIn: Flow<Boolean>
        get() = preferences.isAuthenticated()

}
