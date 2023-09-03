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
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        onboardingScreen(
            onOnboarded = {
                navController.popBackStack()
                navController.navigateToAuth()
            },
        )

        authHomeScreen(
            onExploreApp = {
                navController.popBackStack()
                navController.navigateToHome()
            },
            onGoogleSignup = navController::navigateToSignUp,
            onFacebookSignup = navController::navigateToSignUp,
            onSignup = {
                navController.navigateToSignUp()
            },
            onLogin = {
                navController.navigateToLogin()
            },
        )

        signUpScreen(
            onSignup = {
                navController.popBackStack()
                navController.navigateToHome()
            },
            onLogin = {
                navController.popBackStack()
                navController.navigateToLogin()
            },
            onBack = navController::popBackStack,
        )

        loginScreen(
            onBack = navController::popBackStack,
            onLogin = {
                navController.popBackStack()
                navController.navigateToHome()
            },
            navigateToSignUp = {
                navController.popBackStack()
                navController.navigateToSignUp()
            },
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
