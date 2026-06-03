package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Definimos la expectativa. No hay implementación aquí, solo la "firma".
@Composable
expect fun getDynamicText(): String

@Composable
expect fun getDynamicTextColor(): Color

@Composable
expect fun getDynamicBackgroundColor(): Color