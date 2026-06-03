package ec.edu.epn.aplicacion

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class ValidacionMotoresTest {

    // Obtenemos el contexto real del emulador para acceder al disco duro
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun prepararEntorno() {
        // Limpiamos cualquier rastro de la base de datos antes de cada prueba
        // para garantizar que las pruebas sean aisladas y repetibles.
        context.deleteDatabase("BaseExamenEPN.db")
        File(context.filesDir, "documentos_nosql.json").delete()
    }

    @Test
    fun prueba_MotorSQLite_InsercionYLecturaFisica() {
        // 1. Arrange (Preparar)
        val motorSQL = MotorSQLiteReal(context)
        val datoPrueba = "Registro Relacional Automatizado"

        // 2. Act (Actuar)
        motorSQL.insertar(datoPrueba)
        val resultados = motorSQL.leerTodos()

        // 3. Assert (Verificar)
        assertTrue("La tabla estructurada no debe estar vacía", resultados.isNotEmpty())
        assertEquals("El registro SQL recuperado debe coincidir exactamente", datoPrueba, resultados.last())
    }

    @Test
    fun prueba_MotorNoSQL_CreacionArchivoYJSONDinamico() {
        // 1. Arrange (Preparar)
        val motorNoSQL = MotorNoSQLReal(context)
        val datoPrueba = "Documento JSON Automatizado"

        // 2. Act (Actuar)
        motorNoSQL.guardarDocumento(datoPrueba)
        val resultados = motorNoSQL.leerDocumentos()

        // 3. Assert (Verificar)
        // A. Verificamos la lectura de memoria
        assertTrue("Debe existir al menos un documento JSON", resultados.isNotEmpty())
        assertEquals("El contenido dinámico debe coincidir", datoPrueba, resultados.last())
        
        // B. Verificamos la persistencia física real
        val archivoFisico = File(context.filesDir, "documentos_nosql.json")
        assertTrue("El archivo físico .json debe existir en el disco duro", archivoFisico.exists())
    }
}
