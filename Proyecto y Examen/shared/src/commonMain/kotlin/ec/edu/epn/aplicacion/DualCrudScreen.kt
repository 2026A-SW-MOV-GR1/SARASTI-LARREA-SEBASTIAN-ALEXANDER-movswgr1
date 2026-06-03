package ec.edu.epn.aplicacion


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DualCrudScreen() {
    // Control Switch: Al cambiar este estado, toda la UI conmuta su origen de datos al instante
    var isSqlMode by remember { mutableStateOf(true) }

    // Polimorfismo puro: La variable 'repository' cambia su instancia según la posición del switch
    val repository: DataRepository = if (isSqlMode) SqlRepository() else NoSqlRepository()
    val currentData = repository.getAllItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CRUD Persistencia Dual") },
                actions = {
                    Text(if (isSqlMode) "Modo SQL" else "Modo NoSQL", modifier = Modifier.padding(end = 8.dp))
                    // Switch interactivo en la barra superior
                    Switch(
                        checked = isSqlMode,
                        onCheckedChange = { isSqlMode = it }
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {

            // Indicador de Origen Activo mediante etiquetas claras (Chips visuales)
            SuggestionChip(
                onClick = { },
                label = { Text(if (isSqlMode) "🟢 Origen de datos activo: SQLite Core" else "🟠 Origen de datos activo: Realm NoSQL") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Listado de Datos Recargado:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            // Iteración reactiva sobre la lista del repositorio seleccionado
            currentData.forEach { dataString ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Text(dataString, modifier = Modifier.padding(12.dp))
                }
            }
        }
    }
}