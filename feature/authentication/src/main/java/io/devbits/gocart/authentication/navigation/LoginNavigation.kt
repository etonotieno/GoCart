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
package io.devbits.gocart.authentication.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.devbits.gocart.authentication.ui.login.LoginScreen

const val loginRoute = "authentication/login"

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    this.navigate(loginRoute, navOptions)
}

fun NavGraphBuilder.loginScreen(
    onBack: () -> Unit,
    onLogin: () -> Unit,
    onForgotPassword: () -> Unit,
    navigateToSignUp: () -> Unit,
) {
    composable(route = loginRoute) {
        LoginScreen(
            onBack = onBack,
            onLogin = onLogin,
            navigateToSignUp = navigateToSignUp,
            onForgotPassword = onForgotPassword,
        )
    }
}
