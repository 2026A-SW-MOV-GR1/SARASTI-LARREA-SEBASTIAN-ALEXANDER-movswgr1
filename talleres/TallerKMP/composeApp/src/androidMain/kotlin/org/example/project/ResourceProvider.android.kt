package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import org.example.project.R


// Cumplimos el contrato leyendo los XML nativos
@Composable
actual fun getDynamicText(): String {
    return stringResource(id = R.string.dynamic_text)
}

@Composable
actual fun getDynamicTextColor(): Color {
    return colorResource(id = R.color.dynamic_text_color)
}

@Composable
actual fun getDynamicBackgroundColor(): Color {
    return colorResource(id = R.color.dynamic_bg)
}