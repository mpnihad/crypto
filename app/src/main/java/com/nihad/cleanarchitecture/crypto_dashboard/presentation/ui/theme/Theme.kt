package com.plcoding.cleanarchitecturenoteapp.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.ui.theme.DarkGray
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.ui.theme.LightBlue

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = Color.White,
    background = Color.White,
    onBackground = DarkGray,
    surface = LightBlue,
    onSurface = DarkGray
)

@Composable
fun CleanArchitectureNoteAppTheme(darkTheme: Boolean = false, content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}