package io.devbits.gocart.authentication.ui.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.devbits.gocart.core.datastore.UserPreferences
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UpdatePasswordViewModel @Inject constructor(
    private val preferences: UserPreferences,
) : ViewModel() {

    fun setAuthenticated() {
        viewModelScope.launch {
            preferences.setAuthenticated(true)
        }
    }
}
