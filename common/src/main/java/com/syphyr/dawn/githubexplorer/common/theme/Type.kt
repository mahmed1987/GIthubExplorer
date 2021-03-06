package com.syphyr.dawn.githubexplorer.common.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.syphyr.dawn.githubexplorer.common.R


private val MontserratFontFamily = FontFamily(
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_semi_bold, FontWeight.SemiBold),
    Font(R.font.montserrat_black, FontWeight.Black),
    Font(R.font.montserrat_bold, FontWeight.Bold)
)

val Typography = Typography(
    defaultFontFamily = MontserratFontFamily,
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 96.sp,
        letterSpacing = (-1.5).sp
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 60.sp,
        letterSpacing = (-0.5).sp
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Black,
        fontSize = 48.sp,
        letterSpacing = 0.sp
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        letterSpacing = 0.sp
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = 0.sp
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        letterSpacing = 0.sp
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        letterSpacing = 0.sp
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.sp,
        lineHeight = 24.sp
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        letterSpacing = 0.15.sp
    ),
    overline = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        letterSpacing = 1.sp
    )
)

