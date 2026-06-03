package org.example.project
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun NativeToast(message: String, showToast: Boolean, onDismiss: () -> Unit)
{
    if (showToast) {
        // REQUERIMIENTO CUMPLIDO: Uso de LocalContext.current
        val context = LocalContext.current
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        onDismiss() // Reseteamos el estado
    }
}
