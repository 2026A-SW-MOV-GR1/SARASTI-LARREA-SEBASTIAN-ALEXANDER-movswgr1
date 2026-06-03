package ec.edu.epn.aplicacion

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun RestScreen() {
    val networkService = remember { NetworkService() }
    val scope = rememberCoroutineScope()

    var inputId by remember { mutableStateOf("") }
    var jsonContent by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = inputId,
            onValueChange = { inputId = it },
            label = { Text("ID del Post (Numérico)") },
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                isLoading = true
                scope.launch {
                    jsonContent = networkService.getPost(inputId)
                    isLoading = false
                }
            },
            enabled = !isLoading
        ) {
            // CORRECCIÓN: Uso de Modifier para asignar el tamaño
            if (isLoading) CircularProgressIndicator(modifier = Modifier.size(20.dp)) else Text("Consultar (GET)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = jsonContent,
            onValueChange = { jsonContent = it },
            label = { Text("Contenido del Post (Formulario Editable)") },
            modifier = Modifier.fillMaxWidth().height(150.dp),
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                isLoading = true
                scope.launch {
                    val success = networkService.updatePost(inputId, jsonContent)
                    if (success) {
                        jsonContent = "Confirmación: Código 200 OK capturado con éxito."
                    }
                    isLoading = false
                }
            },
            enabled = !isLoading
        ) {
            Text("Actualizar (PUT)")
        }
    }
}