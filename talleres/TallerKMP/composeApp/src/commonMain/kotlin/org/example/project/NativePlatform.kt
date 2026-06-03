package org.example.project

import androidx.compose.runtime.Composable
// Prometemos un componente que lanzará un mensaje nativo
@Composable
expect fun NativeToast(message: String, showToast: Boolean, onDismiss: () -> Unit)