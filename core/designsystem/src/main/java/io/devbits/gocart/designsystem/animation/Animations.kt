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
package io.devbits.gocart.designsystem.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

private const val DURATION = 300

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideIn() =
    fadeIn(
        animationSpec = tween(DURATION, easing = LinearEasing),
    ) + slideIntoContainer(
        animationSpec = tween(DURATION, easing = EaseIn),
        towards = AnimatedContentTransitionScope.SlideDirection.Start,
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOut() =
    fadeOut(
        animationSpec = tween(DURATION, easing = LinearEasing),
    ) + slideOutOfContainer(
        animationSpec = tween(DURATION, easing = EaseOut),
        towards = AnimatedContentTransitionScope.SlideDirection.End,
    )
