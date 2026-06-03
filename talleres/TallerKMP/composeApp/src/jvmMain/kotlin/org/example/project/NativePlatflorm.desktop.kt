package org.example.project
import androidx.compose.runtime.Composable

@Composable
actual fun NativeToast(message: String, showToast: Boolean, onDismiss: () -> Unit)
{
    if (showToast) {
        println("🖥️ TOAST EN PC: $message") // Simulamos el Toast en consola
        onDismiss()
    }
}