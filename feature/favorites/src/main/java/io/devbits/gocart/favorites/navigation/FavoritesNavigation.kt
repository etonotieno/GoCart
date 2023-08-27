package io.devbits.gocart.favorites.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.devbits.gocart.favorites.ui.FavoritesScreen

const val favoritesRoute = "favorites"

fun NavController.navigateToFavorites(navOptions: NavOptions? = null) {
    this.navigate(favoritesRoute, navOptions)
}

fun NavGraphBuilder.favoritesScreen() {
    composable(route = favoritesRoute) {
        FavoritesScreen(modifier = Modifier)
    }
}