package com.example.vitea.ui.themes


import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = darkBlue,
    primaryVariant = darkBlue,
    secondary = neonGreen,
    background = darkBlue,
    surface = darkBlue,
    onPrimary = Color.White,
    onSurface = Color.White,
    onBackground = Color.White,
    onSecondary = Color.White,
)

@Composable
fun ViteaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = typography,
        shapes = shapes,
        content = content
    )
}