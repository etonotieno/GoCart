package io.devbits.gocart.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.devbits.gocart.designsystem.R

val workSansFontFamily = FontFamily(
    Font(R.font.work_sans),
    Font(R.font.work_sans_bold, FontWeight.Bold),
    Font(R.font.work_sans_medium, FontWeight.Medium),
)

val defaultTypography = Typography()

// Set of Material typography styles to start with
val Typography = Typography(
    // display2*
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = workSansFontFamily),
    // display3*
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = workSansFontFamily),
    // headline1
    displaySmall = TextStyle(
        fontFamily = workSansFontFamily,
        fontSize = 34.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 36.sp,
        letterSpacing = 0.25.sp,
    ),
    // headline2
    headlineLarge = TextStyle(
        fontFamily = workSansFontFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
    ),
    // headline3
    headlineMedium = TextStyle(
        fontFamily = workSansFontFamily,
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        letterSpacing = 0.25.sp,
    ),
    // headline4
    headlineSmall = TextStyle(
        fontFamily = workSansFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
    ),
    // headline5*
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = workSansFontFamily),
    // textAppearanceSubhead1/Subtitle1
    titleMedium = TextStyle(
        fontFamily = workSansFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
    ),
    // textAppearanceSubhead2/Subtitle2
    titleSmall = TextStyle(
        fontFamily = workSansFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp,
    ),
    // textAppearanceBody1
    bodyLarge = TextStyle(
        fontFamily = workSansFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    // textAppearanceBody2
    bodyMedium = TextStyle(
        fontFamily = workSansFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
    ),
    // textAppearanceCaption
    bodySmall = TextStyle(
        fontFamily = workSansFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
    ),
    // textAppearanceButton
    labelLarge = TextStyle(
        fontFamily = workSansFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
        letterSpacing = 1.25.sp,
    ),
    // textAppearanceOverline
    labelMedium = TextStyle(
        fontFamily = workSansFontFamily,
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
        letterSpacing = 1.5.sp,
    ),
    // labelSmall*
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = workSansFontFamily),
)
