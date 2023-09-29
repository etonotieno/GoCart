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
import io.devbits.gocart.authentication.navigation.checkEmailScreen
import io.devbits.gocart.authentication.navigation.forgotPasswordScreen
import io.devbits.gocart.authentication.navigation.locationPermissionScreen
import io.devbits.gocart.authentication.navigation.loginScreen
import io.devbits.gocart.authentication.navigation.navigateToAuth
import io.devbits.gocart.authentication.navigation.navigateToCheckEmail
import io.devbits.gocart.authentication.navigation.navigateToForgotPassword
import io.devbits.gocart.authentication.navigation.navigateToLocationPermission
import io.devbits.gocart.authentication.navigation.navigateToLogin
import io.devbits.gocart.authentication.navigation.navigateToPhoneVerification
import io.devbits.gocart.authentication.navigation.navigateToSignUp
import io.devbits.gocart.authentication.navigation.navigateToUpdatePassword
import io.devbits.gocart.authentication.navigation.phoneVerificationScreen
import io.devbits.gocart.authentication.navigation.signUpScreen
import io.devbits.gocart.authentication.navigation.updatePasswordScreen
import io.devbits.gocart.cart.navigation.cartScreen
import io.devbits.gocart.cart.navigation.navigateToCart
import io.devbits.gocart.favorites.navigation.favoritesScreen
import io.devbits.gocart.homefeed.navigation.homeScreen
import io.devbits.gocart.homefeed.navigation.navigateToHome
import io.devbits.gocart.offers.navigation.offersScreen
import io.devbits.gocart.onboarding.navigation.onboardingScreen
import io.devbits.gocart.payments.navigation.paymentsScreen
import io.devbits.gocart.product.categories.detail.navigation.navigateToProductCategory
import io.devbits.gocart.product.categories.detail.navigation.productCategoryScreen
import io.devbits.gocart.product.categories.navigation.navigateToProductCategories
import io.devbits.gocart.product.categories.navigation.productCategoriesScreen
import io.devbits.gocart.product.details.navigation.navigateToProductDetails
import io.devbits.gocart.product.details.navigation.productDetailsScreen
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
                navController.navigateToPhoneVerification()
            },
            onLogin = {
                navController.popBackStack()
                navController.navigateToLogin()
            },
            onBack = navController::popBackStack,
        )

        phoneVerificationScreen(
            onBack = navController::popBackStack,
            onVerify = {
                navController.popBackStack()
                navController.navigateToLocationPermission()
            },
        )

        locationPermissionScreen(
            onSkip = {
                navController.popBackStack()
                navController.navigateToHome()
            },
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
            onForgotPassword = {
                navController.popBackStack()
                navController.navigateToForgotPassword()
            },
        )

        forgotPasswordScreen(
            onBack = navController::popBackStack,
            onSend = {
                navController.popBackStack()
                navController.navigateToCheckEmail()
            },
        )

        checkEmailScreen(
            onBack = navController::popBackStack,
            onSkip = {
                navController.popBackStack()
                navController.navigateToUpdatePassword()
            },
            onTryAgain = navController::popBackStack,
        )

        updatePasswordScreen(
            onBack = navController::popBackStack,
            onSave = {
                navController.popBackStack()
                navController.navigateToHome()
            },
        )

        homeScreen(
            toCategories = {
                navController.navigateToProductCategories()
            },
            navigateToProduct = { productId ->
                navController.navigateToProductDetails(productId)
            },
        )

        servicesScreen()

        ordersScreen()

        favoritesScreen()

        addressScreen()

        paymentsScreen()

        offersScreen()

        settingsScreen(
            onBack = navController::popBackStack,
        )

        productCategoriesScreen(
            onBack = navController::popBackStack,
            onClickCategory = { name ->
                navController.navigateToProductCategory(name)
            },
        )

        productCategoryScreen(
            onBack = navController::popBackStack,
            navigateToProduct = { productId ->
                navController.navigateToProductDetails(productId)
            },
            navigateToCart = navController::navigateToCart,
        )

        productDetailsScreen(
            onBack = navController::popBackStack,
            onClickProduct = { productId ->
                navController.navigateToProductDetails(productId)
            },
            navigateToCart = navController::navigateToCart,
        )

        cartScreen(
            onBack = navController::popBackStack,
        )
    }
}
