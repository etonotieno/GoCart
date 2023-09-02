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
package io.devbits.gocart.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import io.devbit.gocart.orders.navigation.ordersScreen
import io.devbits.gocart.address.navigation.addressScreen
import io.devbits.gocart.authentication.navigation.authHomeScreen
import io.devbits.gocart.authentication.navigation.loginScreen
import io.devbits.gocart.authentication.navigation.navigateToAuth
import io.devbits.gocart.authentication.navigation.navigateToLogin
import io.devbits.gocart.authentication.navigation.navigateToSignUp
import io.devbits.gocart.authentication.navigation.signUpScreen
import io.devbits.gocart.favorites.navigation.favoritesScreen
import io.devbits.gocart.homefeed.navigation.homeScreen
import io.devbits.gocart.homefeed.navigation.navigateToHome
import io.devbits.gocart.offers.navigation.offersScreen
import io.devbits.gocart.onboarding.navigation.onboardingScreen
import io.devbits.gocart.payments.navigation.paymentsScreen
import io.devbits.gocart.services.navigation.servicesScreen
import io.devbits.gocart.settings.navigation.settingsScreen
import io.devbits.gocart.ui.GoCartAppState

@Composable
fun GoCartNavHost(
    appState: GoCartAppState,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = appState.navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        onboardingScreen(
            onOnboarded = {
                appState.navController.popBackStack()
                appState.navController.navigateToAuth()
            },
        )

        authHomeScreen(
            onExploreApp = {
                appState.navController.popBackStack()
                appState.navController.navigateToHome()
            },
            onGoogleSignup = appState.navController::navigateToSignUp,
            onFacebookSignup = appState.navController::navigateToSignUp,
            onSignup = {
                appState.navController.navigateToSignUp(
                    navOptions {
                        launchSingleTop = true
                    },
                )
            },
            onLogin = {
                appState.navController.navigateToLogin(
                    navOptions {
                        launchSingleTop = true
                    },
                )
            },
        )

        signUpScreen(
            onSignup = {
                appState.navController.popBackStack()
                appState.navController.navigateToHome()
            },
            onLogin = appState.navController::navigateToLogin,
            onBack = appState.navController::popBackStack,
        )

        loginScreen(
            onBack = appState.navController::popBackStack,
            onLogin = {
                appState.navController.popBackStack()
                appState.navController.navigateToHome()
            },
            navigateToSignUp = appState.navController::navigateToSignUp,
            onForgotPassword = {},
        )

        homeScreen()

        servicesScreen()

        ordersScreen()

        favoritesScreen()

        addressScreen()

        paymentsScreen()

        offersScreen()

        settingsScreen()
    }
}
