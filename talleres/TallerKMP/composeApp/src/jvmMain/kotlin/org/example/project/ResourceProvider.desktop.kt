package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
/
@Composable
actual fun getDynamicText(): String {
    return "Soy un programa de Computadora"
}

@Composable
actual fun getDynamicTextColor(): Color {
    return Color.White
}

@Composable
actual fun getDynamicBackgroundColor(): Color {
    return Color.DarkGray
}