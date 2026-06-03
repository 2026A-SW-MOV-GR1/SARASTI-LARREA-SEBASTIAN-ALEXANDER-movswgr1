¡Me parece una iniciativa brillante, Sebastián! Crear documentación que cierre la brecha de conocimiento desde cero absoluto es una de las habilidades más difíciles (y más valoradas) en un Ingeniero de Software.

Para cumplir con este objetivo, he añadido una "Fase 0" de preparación del sistema operativo y he detallado los clics de las instalaciones asumiendo que la computadora está literalmente recién salida de la caja. 

Aquí tienes el manual extendido, estructurado paso a paso y sin dejar nada a la imaginación.

***

# Manual Definitivo (Cero a Cien): Creación de Aplicaciones Multiplataforma

Este manual está diseñado para una persona que acaba de encender su computadora nueva por primera vez. No necesitas experiencia previa en programación para seguirlo. Te guiaremos desde cómo organizar tus archivos hasta ver tu primera aplicación corriendo en un celular virtual y en tu computadora.

---

## Fase 0: Preparar la Computadora Nueva

Las computadoras nuevas vienen con configuraciones para usuarios básicos. Vamos a prepararla para un programador.

### 0.1 Mostrar las extensiones de los archivos (Solo para Windows)
Por defecto, Windows oculta el tipo de archivo (no te muestra si es `.jpg`, `.txt` o `.xml`). Para programar, necesitamos ver esto obligatoriamente.
1. Abre el **Explorador de Archivos** (la carpeta amarilla en la barra de tareas).
2. En la parte superior, haz clic en el menú **Ver** (o *View*).
3. Busca la opción **Mostrar** (o *Show*) y marca la casilla que dice **Extensiones de nombre de archivo** (*File name extensions*).

### 0.2 Crear tu espacio de trabajo
1. Ve a tu carpeta de **Documentos**.
2. Haz clic derecho en un espacio en blanco > **Nuevo** > **Carpeta**.
3. Llámala `Proyectos_Programacion` (evita usar espacios o tildes en los nombres de carpetas para programación). Aquí guardaremos todo.

---

## Fase 1: Descargar e Instalar las Herramientas (Android Studio)

Vamos a instalar tu "Mesa de Trabajo". Usaremos **Android Studio**, que es el programa oficial de Google diseñado para escribir código.

### 1.1 Descarga del Instalador
1. Abre tu navegador de internet (Chrome, Edge, etc.) y ve a esta dirección exacta: `developer.android.com/studio`.
2. Haz clic en el botón verde gigante que dice **Download Android Studio**.
3. Baja hasta el final de los términos y condiciones, marca la casilla de aceptar y haz clic en descargar. (Pesa alrededor de 1 GB, así que puede tardar unos minutos).

### 1.2 Instalación paso a paso
1. Una vez descargado, abre el archivo (suele llamarse `android-studio-202X...exe`).
2. Si Windows te pregunta "¿Quieres permitir que esta aplicación haga cambios?", dile que **Sí**.
3. En la ventana de instalación, simplemente haz clic en **Next** (Siguiente) a todo. Asegúrate de que la casilla "Android Virtual Device" esté marcada si te la muestra.
4. Al final, haz clic en **Install** y luego en **Finish**.



### 1.3 Configuración del primer encendido (SDK)
* **¿Qué es el SDK?** Son los "planos de construcción" que necesita Android Studio para saber cómo fabricar una aplicación.
1. Al abrir Android Studio por primera vez, te saldrá una ventana de bienvenida. Haz clic en **Next**.
2. Elige el tipo de instalación **Standard** (Estándar) y dale a **Next**.
3. Elige el color que prefieras para tu programa (Oscuro o Claro) y dale a **Next**.
4. Te mostrará una lista de cosas por descargar. Haz clic en **Next**.
5. **Importante:** Te pedirá aceptar unas licencias. A la izquierda verás varias carpetas con un asterisco rojo o un símbolo de exclamación. Haz clic en cada una de ellas y luego, a la derecha, marca la bolita que dice **Accept**.
6. Cuando el botón **Finish** se ponga azul, hazle clic.
7. Espera a que la barra de descarga termine (descargará las herramientas de Google en tu computadora). Cuando termine, dale a **Finish**.

---

## Fase 2: Descargar el Código Base (KMP Wizard)

En lugar de crear un proyecto vacío, usaremos la página oficial para que nos entregue los cimientos de la casa ya construidos.

### 2.1 Generar el proyecto
1. Abre tu navegador de internet y entra a `kmp.jetbrains.com`.
2. En el menú del centro, asegúrate de hacer clic en **Compose Multiplatform** (esto nos permitirá dibujar la aplicación una sola vez).
3. En la lista de plataformas, marca **Android** y marca **Desktop**.
4. En el campo "Project Name", borra lo que está escrito y pon: `MiPrimerTaller`.
5. Haz clic en el botón **Download** abajo a la derecha. Se descargará un archivo comprimido llamado `MiPrimerTaller.zip`.

### 2.2 Descomprimir y abrir
1. Ve a tu carpeta de **Descargas** en la computadora.
2. Haz **clic derecho** sobre `MiPrimerTaller.zip` y selecciona **Extraer todo...** (o *Extract All*).
3. Selecciona la carpeta `Proyectos_Programacion` que creamos en la Fase 0 y dale a Extraer.
4. Abre **Android Studio**. En la ventana principal, haz clic en el botón que dice **Open** (Abrir).
5. Busca la carpeta `Proyectos_Programacion`, selecciona la carpeta `MiPrimerTaller` (asegúrate de seleccionar la carpeta normal, no el archivo con un cierre). Haz clic en **OK**.
6. **PAUSA OBLIGATORIA:** Al abrirse el programa, verás una barra cargando en la esquina inferior derecha que dice "Gradle Sync". Tu computadora está conectándose a internet para descargar las librerías necesarias. **No toques absolutamente nada** hasta que esa barra desaparezca y aparezca un visto verde (puede tardar de 5 a 10 minutos la primera vez).



---

## Fase 3: Escribir el Código (La Magia)

Aquí vamos a crear el sistema para que nuestra aplicación cambie de idioma y color por sí sola al girar la pantalla.

### 3.1 Crear las Carpetas de Recursos (Los menús del restaurante)
Vamos a crear "carpetas inteligentes" en la sección exclusiva de Android.
1. En Android Studio, mira la barra de la izquierda (llamada *Project*). Haz clic en la flechita para expandir la carpeta principal `MiPrimerTaller`.
2. Navega por esta ruta exacta expandiendo las carpetas: `composeApp` > `src` > `androidMain`.
3. Haz **clic derecho** sobre la carpeta `androidMain` > selecciona **New** > luego **Directory**.
4. Escribe `res` y presiona **Enter**.
5. Ahora haz **clic derecho** sobre esa nueva carpeta `res` > **New** > **Directory**.
6. Escribe `values` y presiona Enter. (Esta será la carpeta para Español en vertical).

Repite los pasos 5 y 6 tres veces más para crear estas carpetas dentro de `res`:
* `values-en` (Para inglés en vertical)
* `values-land` (Para español en horizontal)
* `values-en-land` (Para inglés en horizontal)

### 3.2 Llenar las carpetas con colores y textos
1. Haz **clic derecho** sobre tu primera carpeta `values` > **New** > **File**.
2. Llámalo `strings.xml` (presiona Enter). Pega este texto exactamente:
```xml
<resources>
    <string name="dynamic_text">Texto A (Español)</string>
</resources>
```
3. Haz **clic derecho** de nuevo en `values` > **New** > **File**. Llámalo `colors.xml` y pega:
```xml
<resources>
    <color name="dynamic_bg">#FFFFFF</color> 
    <color name="dynamic_text_color">#000000</color>
</resources>
```
4. **Tu Tarea:** Repite este proceso de crear `strings.xml` y `colors.xml` en las otras tres carpetas (`values-en`, `values-land`, `values-en-land`). Pero, cambia los textos (ej. "Texto B (Inglés)") y los colores (ej. cambia `#FFFFFF` por `#FF0000` que es rojo).



### 3.3 Crear la Promesa (El archivo Compartido)
Vamos a decirle a la aplicación central que prometa conseguirnos colores, sin importar si estamos en un celular o en una computadora.
1. En la barra izquierda, navega por: `composeApp` > `src` > `commonMain` > `kotlin` > `org.example.project`.
2. Haz **clic derecho** sobre `org.example.project` > **New** > **Kotlin Class/File**.
3. Escribe `ResourceProvider` y presiona **Enter**.
4. Pega esto:
```kotlin
package org.example.project 

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// expect = "Prometo que cada sistema conseguirá estos datos"
@Composable
expect fun getDynamicText(): String

@Composable
expect fun getDynamicTextColor(): Color

@Composable
expect fun getDynamicBackgroundColor(): Color
```

### 3.4 Cumplir la promesa en Android
1. Navega por: `composeApp` > `src` > `androidMain` > `kotlin` > `org.example.project`.
2. Haz **clic derecho** sobre `org.example.project` > **New** > **Kotlin Class/File**.
3. Escribe `ResourceProvider.android` y presiona **Enter**.
4. Pega esto (estamos conectando el código con las carpetas XML que hicimos en el paso 3.1):
```kotlin
package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import org.example.project.R // Esto conecta con tus carpetas res

// actual = "Esta es la realidad en Android"
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
```

### 3.5 Cumplir la promesa en la Computadora (Desktop)
1. Navega por: `composeApp` > `src` > `desktopMain` > `kotlin` > `org.example.project` (si la carpeta se llama `jvmMain`, usa esa).
2. Haz **clic derecho** > **New** > **Kotlin Class/File**. Escribe `ResourceProvider.desktop` y dale a Enter.
3. Pega esto (como la PC no tiene carpetas, le damos texto y colores fijos):
```kotlin
package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

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
```

### 3.6 Dibujar la Pantalla Final
1. Ve a la carpeta `commonMain` > `kotlin` > `org.example.project` y abre el archivo **`App.kt`**.
2. Borra todo su contenido y pega esto (es la caja y el texto visual):
```kotlin
package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun App() {
    val currentBgColor = getDynamicBackgroundColor()
    val currentTextColor = getDynamicTextColor()
    val currentText = getDynamicText()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(currentBgColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = currentText,
            color = currentTextColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
```

### 3.7 El Escudo (Evitar que el celular cierre la app)
Vamos a usar un "escudo" llamado configChanges. Al poner esto, le decimos a Android: "No destruyas la app, yo me encargo de los cambios". Gracias a esto, nuestra herramienta visual (Compose) simplemente redibuja los colores nuevos en tiempo real sin cerrar la aplicación.
1. Ve a `composeApp` > `src` > `androidMain`.
2. Abre el archivo **`AndroidManifest.xml`**.
3. Busca la palabra `<activity` y añade la línea `android:configChanges` justo debajo de `android:name=".MainActivity"`. Debe quedar exactamente así:
```xml
<activity
    android:name=".MainActivity"
    android:configChanges="orientation|screenSize|screenLayout|keyboardHidden|locale|layoutDirection"
    android:exported="true">
```

---

## Fase 4: La Prueba Final (Ver tu obra de arte)

¡Llegó el momento de ver el resultado en la computadora y en un celular falso!

### Prueba 1: Programa de Computadora (Desktop)
1. Mira la barra superior de Android Studio. Hay una caja de selección al lado de un martillo verde y un botón de Play verde (`▶`).
2. Haz clic en esa caja y selecciona **desktopApp**.
3. Haz clic en el **botón Play (▶)**.
4. Espera unos segundos. ¡Felicidades! Se abrirá una ventana de programa en tu computadora con fondo oscuro y texto blanco que dice *"Soy un programa de Computadora"*. (Ciérrala usando la X de la ventana para continuar).

### Prueba 2: Celular (Android)
Necesitamos crear el "Celular falso" dentro de la pantalla.
1. En la parte superior derecha, haz clic en un ícono de un teléfono con el símbolo de Android (se llama **Device Manager**).
2. Haz clic en el símbolo de suma (`+`) o en **Create Virtual Device**.
3. En la lista, selecciona **Pixel 7** y haz clic en **Next**.
4. Te mostrará versiones de Android. Al lado de una que diga API 34 o 35, habrá un botón azul de **Download** (Descargar). Hazle clic, espera a que termine, dale a **Finish**.
5. Ahora selecciona esa versión descargada, haz clic en **Next** y luego en **Finish**.
6. Cierra la pestaña de Device Manager.
7. Ve arriba, donde habías seleccionado *desktopApp*, haz clic y cámbialo por **composeApp** (o `androidApp`).
8. Justo a la derecha de eso, asegúrate de que esté seleccionado tu celular "Pixel 7".
9. ¡Haz clic en el **botón Play (▶)**!

Se abrirá un celular en tu pantalla y, en unos segundos, tu aplicación arrancará. 

**Para probar la magia:** Haz clic en los íconos de "Girar" en la barrita que sale junto al celular virtual. Verás cómo los colores y el texto cambian al instante entre el Texto A y el Texto C sin que la aplicación se congele. ¡Has creado tu primera aplicación multiplataforma con éxito!.
# PARTE 2
¡Entendido, Sebastián! Ya que tu tecnología asignada es **KMP + Compose**, vamos a construir este Mock-CRUD directamente sobre el proyecto `TallerKMP` que configuramos en la clase anterior.

A este nivel de la carrera de Ingeniería de Sistemas, entender el árbol de renderizado a bajo nivel es lo que marca la diferencia en el diseño de software. Analizar cómo los componentes gráficos se comunican con el sistema operativo es una habilidad técnica rigurosa que sin duda elevará la calidad de tus análisis y reportes técnicos en el Laboratorio ADN Software.

Para cumplir exactamente con la rúbrica (Material 3, cero librerías externas, y el puente nativo para el Toast), vamos a usar el mismo poder del patrón `expect`/`actual` que aprendiste antes, pero esta vez para invocar hardware y contexto nativo de Android.

Aquí tienes el manual definitivo, paso a paso, para tu Taller de Clase 04.

---

# Manual Práctico: CRUD Visual y Renderizado en KMP

### Contexto del Framework (KMP + Compose)

A diferencia de React Native o NativeScript que instancian componentes de Android (`android.widget.TextView`), Compose dibuja **cada píxel directamente sobre un Canvas** (lienzo). Por lo tanto, tu interfaz será extremadamente rápida y fluida, pero requerirá que crucemos un "puente" cuando queramos usar un servicio antiguo del sistema, como un `Toast`.

---

## Paso 1: Configurar el Reto Nativo (El Toast)

El profesor pide explícitamente usar `LocalContext.current` para el Toast. Como el código de nuestra interfaz vive en `commonMain` (que no sabe qué es un "Context" de Android), crearemos un componente puente.

**1.1. En `commonMain` (La Promesa)**

1. Ve a `composeApp > src > commonMain > kotlin > org.example.project`.
2. Crea un archivo llamado `NativePlatform.kt`.
3. Declara la promesa de un Toast nativo:

```kotlin
package org.example.project

import androidx.compose.runtime.Composable

// Prometemos un componente que lanzará un mensaje nativo
@Composable
expect fun NativeToast(message: String, showToast: Boolean, onDismiss: () -> Unit)

```

**1.2. En `androidMain` (La Realidad en Android usando LocalContext)**

1. Ve a `composeApp > src > androidMain > kotlin > org.example.project`.
2. Crea un archivo llamado `NativePlatform.android.kt`.
3. Cumplimos el requerimiento del profesor accediendo al contexto de Android:

```kotlin
package org.example.project

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun NativeToast(message: String, showToast: Boolean, onDismiss: () -> Unit) {
    if (showToast) {
        // REQUERIMIENTO CUMPLIDO: Uso de LocalContext.current
        val context = LocalContext.current 
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        onDismiss() // Reseteamos el estado
    }
}

```

**1.3. En `desktopMain` o `jvmMain` (Para que la PC no explote)**

1. Ve a la carpeta de PC y crea `NativePlatform.desktop.kt`.

```kotlin
package org.example.project

import androidx.compose.runtime.Composable

@Composable
actual fun NativeToast(message: String, showToast: Boolean, onDismiss: () -> Unit) {
    if (showToast) {
        println("🖥️ TOAST EN PC: $message") // Simulamos el Toast en consola
        onDismiss()
    }
}

```

---

## Paso 2: Modelo de Datos y Estado de Navegación

Como no podemos usar librerías externas (ni de navegación ni de bases de datos), haremos todo con estados simples en memoria.

1. En `commonMain > kotlin > org.example.project`, abre tu archivo **`App.kt`**.
2. Al inicio del archivo (fuera de cualquier función), agrega nuestro modelo de datos y las pantallas:

```kotlin
// Datos quemados (Hardcoded)
data class Contact(val id: Int, val name: String, val role: String)

val dummyData = mutableListOf(
    Contact(1, "Alan Turing", "Matemático"),
    Contact(2, "Ada Lovelace", "Programadora"),
    Contact(3, "Linus Torvalds", "Ingeniero de Software")
)

// Gestor de Navegación simple
enum class ScreenType { LIST, FORM }

```

---

## Paso 3: Dibujar la Interfaz (Cumpliendo Material 3)

Ahora, vamos a reemplazar la función `@Composable fun App()` que tenías, con las pantallas que pide el taller. Pega todo este código debajo del bloque anterior en **`App.kt`**.

### 3.1 La Lógica Principal

```kotlin
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
import androidx.compose.ui.unit.dp

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

```

### 3.2 Pantalla de Lista (Read y Dialog de Delete)

Esta pantalla implementa la lista, los bordes redondeados y el Dialog nativo de Compose. Pégalo a continuación:

```kotlin
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
        LazyColumn(contentPadding = padding, modifier = Modifier.fillMaxSize().padding(16.dp)) {
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
                        Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(40.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(contact.name, style = MaterialTheme.typography.titleMedium)
                            Text(contact.role, style = MaterialTheme.typography.bodyMedium)
                        }
                        IconButton(onClick = { contactToDelete = contact }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
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
                        Text("Sí, eliminar", color = MaterialTheme.colorScheme.error)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { contactToDelete = null }) { Text("Cancelar") }
                }
            )
        }
    }
}

```

### 3.3 Pantalla de Formulario (Create/Update)

Pega esto al final para cumplir con los inputs y selectores (switches).

```kotlin
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
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedButton(onClick = onNavigateBack) { Text("Cancelar") }
            Button(onClick = onNavigateBack) { Text("Guardar") }
        }
    }
}

```

---

¡Con gusto, Sebastián! Tienes toda la razón en pedir esta corrección. Ya que vimos exactamente cómo se muestra tu Layout Inspector en la vida real (con los cubitos azules de Compose), debemos ajustar ese paso en el manual para que coincida 100% con lo que vas a capturar y con el argumento exacto que vas a presentar.

Aquí tienes la **Fase Final corregida**, lista para que la reemplaces en tu documento:

---

## Fase Final: Capturar el Entregable (Layout Inspector)

Aquí es donde demuestras académicamente por qué KMP es diferente a los frameworks híbridos.

1. **Ejecuta la app** en tu emulador de Android (asegúrate de seleccionar `composeApp`).
2. En Android Studio, ve a la barra superior y selecciona **Tools** > **Layout Inspector** (o busca el ícono de las capas superpuestas en la barra derecha).
3. Asegúrate de seleccionar tu emulador activo y el proceso de tu app en la parte superior izquierda de la ventana del Inspector.
4. **Analizando el Component Tree:** A la izquierda verás la jerarquía. Expande todo hasta que llegues al núcleo de tu lista.
5. **Lo que debes destacar en tu captura:** Al desplegar el árbol, notarás que todos los elementos (como `App`, `MaterialTheme`, `Scaffold`, `LazyColumn`, y `ElevatedCard`) tienen un ícono de un **cubito azul**. Esto significa que son "Nodos de Compose". **No existen** las clásicas etiquetas nativas de Android como `android.widget.TextView` o `android.widget.Button` por cada elemento que ves en la pantalla.
6. **Tu argumento en el reporte:** *"Como se evidencia en la captura del Layout Inspector, Kotlin Multiplatform y Jetpack Compose no instancian múltiples Widgets nativos por cada elemento visual (a diferencia de React Native o NativeScript). La jerarquía está compuesta exclusivamente por nodos declarativos puros. Todo cuelga de un solo contenedor raíz que actúa como un lienzo (Canvas) donde el motor gráfico dibuja los elementos directamente, logrando un rendimiento superior, y cruzando el puente nativo únicamente para servicios específicos del sistema operativo, como acceder al LocalContext para los Toasts."*
