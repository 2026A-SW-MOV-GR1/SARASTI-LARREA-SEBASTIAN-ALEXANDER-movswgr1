package ec.edu.epn.aplicacion

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class NetworkService {
    // HttpClient es el motor encargado de abrir los sockets de red y gestionar el protocolo HTTP
    private val client = HttpClient()

    // Usamos 'suspend' porque la petición de red tarda un tiempo indeterminado.
    // Esto le permite a Kotlin pausar la función sin bloquear la pantalla de la app.
    suspend fun getPost(id: String): String {
        return try {
            // Disparador GET a la URL dinámica con el ID proporcionado por el usuario
            val response = client.get("https://jsonplaceholder.typicode.com/posts/$id")
            response.bodyAsText() // Retorna el cuerpo de la respuesta en formato JSON crudo
        } catch (e: Exception) {
            "Error de conexión: ${e.message}"
        }
    }

    // Petición PUT para simular la actualización del recurso
    suspend fun updatePost(id: String, jsonBody: String): Boolean {
        return try {
            val response = client.put("https://jsonplaceholder.typicode.com/posts/$id") {
                setBody(jsonBody) // Adjuntamos el JSON modificado en el cuerpo del mensaje
            }
            // Capturamos el código de estado 200 OK para confirmar el éxito de la operación
            response.status.value == 200
        } catch (e: Exception) {
            false
        }
    }
}