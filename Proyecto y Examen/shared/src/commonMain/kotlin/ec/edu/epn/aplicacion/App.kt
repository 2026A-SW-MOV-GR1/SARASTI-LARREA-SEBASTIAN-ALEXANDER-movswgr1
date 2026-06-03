package ec.edu.epn.aplicacion

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

// ==========================================
// 1. LOS CONTRATOS (PUENTES DE HARDWARE)
// ==========================================
interface PuenteSeguridad {
    suspend fun guardarDatos(llave: String, valor: String, metodo: Int)
    suspend fun recuperarDatos(llave: String, metodo: Int): String
}

interface PuentePersistenciaDual {
    fun guardarSQL(dato: String)
    fun leerSQL(): List<String>
    fun guardarNoSQL(dato: String)
    fun leerNoSQL(): List<String>
}

// ==========================================
// 2. ENRUTADOR PRINCIPAL
// ==========================================
@Composable
fun App(puenteSeguridad: PuenteSeguridad? = null, puenteBD: PuentePersistenciaDual? = null) {
    MaterialTheme {
        var pantallaActual by remember { mutableStateOf("MENU") }

        Scaffold(
            topBar = {
                if (pantallaActual != "MENU") {
                    Button(onClick = { pantallaActual = "MENU" }, modifier = Modifier.padding(16.dp)) {
                        Text("← Volver al Menú Principal")
                    }
                }
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) {
                when (pantallaActual) {
                    "MENU" -> MenuPrincipal { pantallaSeleccionada -> pantallaActual = pantallaSeleccionada }
                    "DUAL" -> PantallaPersistenciaDual(puenteBD)
                    "SECURE" -> PantallaSeguridadVisual(puenteSeguridad)
                    // "REST" -> RestScreen() // Descomenta si tienes el archivo del Módulo 1 aparte
                }
            }
        }
    }
}

@Composable
fun MenuPrincipal(onNavigate: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Laboratorio de Ingeniería Móvil FIS", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { onNavigate("DUAL") }, modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp)) {
            Text("Módulo 2: Persistencia Dual FÍSICA")
        }
        Button(onClick = { onNavigate("SECURE") }, modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp)) {
            Text("Módulo 3: Almacenamiento Seguro")
        }
    }
}

// ==========================================
// 3. PANTALLA: PERSISTENCIA DUAL (MÓDULO 2)
// ==========================================
@Composable
fun PantallaPersistenciaDual(puenteBD: PuentePersistenciaDual?) {
    var inputDato by remember { mutableStateOf("") }
    var isSql by remember { mutableStateOf(true) } // true = SQL, false = NoSQL
    var listaDatos by remember { mutableStateOf(listOf<String>()) }
    var mensajeConsola by remember { mutableStateOf("") }

    // Refrescar la lista de la base de datos real al cambiar el switch
    LaunchedEffect(isSql) {
        if (puenteBD != null) {
            listaDatos = if (isSql) puenteBD.leerSQL() else puenteBD.leerNoSQL()
        }
    }

    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Motores de Base de Datos", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(if (isSql) "🗄️ Motor: SQLite" else "📄 Motor: JSON Dinámico")
            Switch(checked = isSql, onCheckedChange = { isSql = it }, modifier = Modifier.padding(start = 8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputDato, onValueChange = { inputDato = it }, label = { Text("Nuevo Dato") })
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (puenteBD != null && inputDato.isNotEmpty()) {
                if (isSql) {
                    puenteBD.guardarSQL(inputDato)
                    listaDatos = puenteBD.leerSQL() // Actualiza UI leyendo del disco
                    mensajeConsola = "Guardado físicamente en .db"
                } else {
                    puenteBD.guardarNoSQL(inputDato)
                    listaDatos = puenteBD.leerNoSQL() // Actualiza UI leyendo del disco
                    mensajeConsola = "Guardado físicamente en .json"
                }
                inputDato = ""
            }
        }) { Text("Insertar en Disco") }

        Spacer(modifier = Modifier.height(16.dp))
        Text(mensajeConsola, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
            Text("Registros recuperados del disco duro:", style = MaterialTheme.typography.labelLarge)
            listaDatos.forEach { Text("• $it") }
        }
    }
}

// ==========================================
// 4. PANTALLA: SEGURIDAD (MÓDULO 3)
// ==========================================
@Composable
fun PantallaSeguridadVisual(puente: PuenteSeguridad?) {
    val scope = rememberCoroutineScope()
    var keyInput by remember { mutableStateOf("") }
    var valueInput by remember { mutableStateOf("") }
    var mensajeConsola by remember { mutableStateOf("") }
    var metodoSeleccionado by remember { mutableStateOf(2) } // 0=Plano, 1=Cifrado, 2=DataStore

    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Gestión de Secretos", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = keyInput, onValueChange = { keyInput = it }, label = { Text("Llave (Key)") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = valueInput, onValueChange = { valueInput = it }, label = { Text("Valor (Value)") })

        Spacer(modifier = Modifier.height(16.dp))
        Column(horizontalAlignment = Alignment.Start) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = metodoSeleccionado == 0, onClick = { metodoSeleccionado = 0 })
                Text("📄 SharedPreferences (Plano)")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = metodoSeleccionado == 1, onClick = { metodoSeleccionado = 1 })
                Text("🔒 EncryptedSharedPrefs (Cifrado)")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = metodoSeleccionado == 2, onClick = { metodoSeleccionado = 2 })
                Text("⚡ DataStore (Binario)")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                if (puente != null && keyInput.isNotEmpty()) {
                    scope.launch {
                        puente.guardarDatos(keyInput, valueInput, metodoSeleccionado)
                        mensajeConsola = "Secreto inyectado en el hardware."
                    }
                }
            }) { Text("Guardar") }

            Button(onClick = {
                if (puente != null && keyInput.isNotEmpty()) {
                    scope.launch {
                        val valorRecuperado = puente.recuperarDatos(keyInput, metodoSeleccionado)
                        mensajeConsola = if (valorRecuperado.contains("Error")) valorRecuperado else "Valor recuperado: $valorRecuperado"
                    }
                }
            }) { Text("Recuperar") }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(mensajeConsola, color = MaterialTheme.colorScheme.primary)
    }
}