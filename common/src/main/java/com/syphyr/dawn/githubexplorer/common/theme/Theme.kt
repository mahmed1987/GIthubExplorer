package com.syphyr.dawn.githubexplorer.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = GhostWhite,
    primaryVariant = BlueGrey400,
    onPrimary = Woodsmoke,
    secondary = Lime100,
    secondaryVariant = Teal50,
    onSecondary = Black,
    surface= BlueGrey500,
    onSurface = GhostWhite,
    background = Woodsmoke,
    onBackground = GhostWhite
)

private val LightColorPalette = lightColors(
    primary = Woodsmoke,
    primaryVariant = BlueGrey400,
    onPrimary = GhostWhite,
    secondary = BlueGrey300,
    secondaryVariant = Teal50,
    onSecondary = Black,
    surface= Selago,
    onSurface = Black,
    background = GhostWhite,
    onBackground = Woodsmoke
)

//To obtain the color below
//  For Dark Mode
// , I applied a LocalElevationOverlay of 4.dp on the background color. Took a screenshot and
//   ran it through a color picker.
// For Light Mode
//   Trial and error

val Colors.topAppBar: Color
    get() = if (isLight) Selago else Shark


@Composable
fun GithubExplorerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}