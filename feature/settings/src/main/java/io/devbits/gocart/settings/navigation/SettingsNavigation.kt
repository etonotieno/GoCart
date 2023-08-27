package io.devbits.gocart.settings.navigation

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.devbits.gocart.composeui.components.SystemBars
import io.devbits.gocart.settings.ui.SettingsScreen

const val settingsRoute = "settings"

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(settingsRoute, navOptions)
}

fun NavGraphBuilder.settingsScreen() {
    composable(route = settingsRoute) {
        SystemBars(themed = true)
        SettingsScreen(modifier = Modifier.safeDrawingPadding())
    }
}
