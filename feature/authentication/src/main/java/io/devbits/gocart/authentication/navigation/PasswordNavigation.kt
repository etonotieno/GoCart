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
import io.devbits.gocart.authentication.ui.password.CheckEmailScreen
import io.devbits.gocart.authentication.ui.password.ForgotPasswordScreen
import io.devbits.gocart.authentication.ui.password.UpdatePasswordRoute

const val forgotPasswordRoute = "authentication/forgot_password"

fun NavController.navigateToForgotPassword(navOptions: NavOptions? = null) {
    this.navigate(forgotPasswordRoute, navOptions)
}

fun NavGraphBuilder.forgotPasswordScreen(onBack: () -> Unit, onSend: () -> Unit) {
    composable(route = forgotPasswordRoute) {
        ForgotPasswordScreen(onBack = onBack, onSend = onSend)
    }
}

const val checkEmailRoute = "authentication/check_email"

fun NavController.navigateToCheckEmail(navOptions: NavOptions? = null) {
    this.navigate(checkEmailRoute, navOptions)
}

fun NavGraphBuilder.checkEmailScreen(
    onBack: () -> Unit,
    onSkip: () -> Unit,
    onTryAgain: () -> Unit,
) {
    composable(route = checkEmailRoute) {
        CheckEmailScreen(onBack = onBack, onSkip = onSkip, onTryAgain = onTryAgain)
    }
}

const val updatePasswordRoute = "authentication/update_password"

fun NavController.navigateToUpdatePassword(navOptions: NavOptions? = null) {
    this.navigate(updatePasswordRoute, navOptions)
}

fun NavGraphBuilder.updatePasswordScreen(onBack: () -> Unit, onSave: () -> Unit) {
    composable(route = updatePasswordRoute) {
        UpdatePasswordRoute(onBack = onBack, onSave = onSave)
    }
}
