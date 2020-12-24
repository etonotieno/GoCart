package io.devbits.onboarding.ui.model

import androidx.annotation.DrawableRes

data class OnboardingItem(
    @DrawableRes
    val imageRes: Int,
    val title: String,
    val description: String
)
