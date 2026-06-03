package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

//import org.example.project.screens.ContactListScreen

import androidx.compose.ui.unit.dp

// Datos quemados (Hardcoded)
data class Contact(val id: Int, val name: String, val role: String)
val dummyData = mutableListOf(
    Contact(1, "Alan Turing", "Matemático"),
    Contact(2, "Ada Lovelace", "Programadora"),
    Contact(3, "Linus Torvalds", "Ingeniero de Software")
)
// Gestor de Navegación simple
enum class ScreenType { LIST, FORM }

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf(ScreenType.LIST) }
    var showToast by remember { mutableStateOf(false) }
    // El puente nativo que creamos en el Paso 1
    NativeToast(
        message = "Eliminado con éxito",
        showToast = showToast,
        onDismiss = { showToast = false }
    )
    MaterialTheme { // Aplica estilos Material 3 por defecto
        Surface(modifier = Modifier.fillMaxSize()) {
            when (currentScreen) {
                ScreenType.LIST -> ContactListScreen(
                    onNavigateToForm = { currentScreen = ScreenType.FORM },
                    onDeleteSuccess = { showToast = true }
                )
                ScreenType.FORM -> ContactFormScreen(
                            onNavigateBack = { currentScreen = ScreenType.LIST }
                )
            }
        }
    }
}
@Composable
fun ContactListScreen(onNavigateToForm: () -> Unit, onDeleteSuccess: () -> Unit) {
    var contactToDelete by remember { mutableStateOf<Contact?>(null) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToForm) {
                Icon(Icons.Default.Add, contentDescription = "Crear")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding, modifier =
            Modifier.fillMaxSize().padding(16.dp)) {
            items(dummyData) { contact ->
                // ElevatedCard cumple con las guías de elevación y bordes redondeados de M3
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    onClick = onNavigateToForm // Simula editar
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null,
                            modifier = Modifier.size(40.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(contact.name, style =
                                MaterialTheme.typography.titleMedium)
                            Text(contact.role, style =
                                MaterialTheme.typography.bodyMedium)
                        }
                        IconButton(onClick = { contactToDelete = contact }) {
                            Icon(Icons.Default.Delete, contentDescription =
                                "Eliminar", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }
        // Flujo de Eliminación (Dialog)
        contactToDelete?.let { contact ->
            AlertDialog(
                onDismissRequest = { contactToDelete = null },
                title = { Text("Eliminar Registro") },
                text = { Text("¿Estás seguro de eliminar a ${contact.name}?") },
                confirmButton = {
                    TextButton(onClick = {
                        dummyData.remove(contact)
                        contactToDelete = null
                        onDeleteSuccess() // Lanza el Toast Nativo
                    }) {
                        Text("Sí, eliminar", color =
                            MaterialTheme.colorScheme.error)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { contactToDelete = null }) {
                        Text("Cancelar") }
                }
            )
        }
    }
}

@Composable
fun ContactFormScreen(onNavigateBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(true) }
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Nuevo Registro", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre Completo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = role,
            onValueChange = { role = it },
            label = { Text("Cargo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Usuario Activo", modifier = Modifier.weight(1f))
            Switch(checked = isActive, onCheckedChange = { isActive = it })
        }

        Spacer(modifier = Modifier.weight(1f)) // Empuja los botones abajo

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement =
            Arrangement.SpaceBetween) {
            OutlinedButton(onClick = onNavigateBack) { Text("Cancelar") }
            Button(onClick = onNavigateBack) { Text("Guardar") }
        }
    }
}
