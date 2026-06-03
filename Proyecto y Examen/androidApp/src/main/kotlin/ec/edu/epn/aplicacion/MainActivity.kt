package ec.edu.epn.aplicacion

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.flow.first
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

// ==========================================
// MOTOR 1: SQL RELACIONAL
// ==========================================
class MotorSQLiteReal(context: Context) : SQLiteOpenHelper(context, "BaseExamenEPN.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE registros (id INTEGER PRIMARY KEY AUTOINCREMENT, valor TEXT)")
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    fun insertar(texto: String) {
        val db = this.writableDatabase
        val valores = ContentValues().apply { put("valor", texto) }
        db.insert("registros", null, valores)
        db.close()
    }

    fun leerTodos(): List<String> {
        val lista = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT valor FROM registros", null)
        if (cursor.moveToFirst()) {
            do { lista.add(cursor.getString(0)) } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return lista
    }
}

// ==========================================
// MOTOR 2: NOSQL BASADO EN DOCUMENTOS
// ==========================================
class MotorNoSQLReal(private val context: Context) {
    private val nombreArchivo = "documentos_nosql.json"

    fun guardarDocumento(texto: String) {
        val archivo = File(context.filesDir, nombreArchivo)
        val arrayActual = if (archivo.exists()) JSONArray(archivo.readText()) else JSONArray()

        val nuevoDocumento = JSONObject().apply {
            put("timestamp", System.currentTimeMillis())
            put("contenido", texto)
        }
        arrayActual.put(nuevoDocumento)
        archivo.writeText(arrayActual.toString())
    }

    fun leerDocumentos(): List<String> {
        val archivo = File(context.filesDir, nombreArchivo)
        if (!archivo.exists()) return emptyList()

        val lista = mutableListOf<String>()
        val array = JSONArray(archivo.readText())
        for (i in 0 until array.length()) {
            val doc = array.getJSONObject(i)
            lista.add(doc.getString("contenido"))
        }
        return lista
    }
}

// ==========================================
// LA ACTIVIDAD PRINCIPAL (EL CASCARÓN)
// ==========================================
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // --- HARDWARE MÓDULO 2: BASES DE DATOS ---
        val motorSQL = MotorSQLiteReal(this)
        val motorNoSQL = MotorNoSQLReal(this)

        val puenteBaseDeDatos = object : PuentePersistenciaDual {
            override fun guardarSQL(dato: String) = motorSQL.insertar(dato)
            override fun leerSQL(): List<String> = motorSQL.leerTodos()
            override fun guardarNoSQL(dato: String) = motorNoSQL.guardarDocumento(dato)
            override fun leerNoSQL(): List<String> = motorNoSQL.leerDocumentos()
        }

        // --- HARDWARE MÓDULO 3: SEGURIDAD ---
        val masterKey = MasterKey.Builder(this)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        val dataStore = PreferenceDataStoreFactory.create(
            produceFile = { File(applicationContext.filesDir, "datastore/ModernConfig.preferences_pb") }
        )

        val puenteSeguridad = object : PuenteSeguridad {
            override suspend fun guardarDatos(llave: String, valor: String, metodo: Int) {
                when (metodo) {
                    0 -> {
                        getSharedPreferences("PlainConfig", Context.MODE_PRIVATE).edit().putString(llave, valor).apply()
                    }
                    1 -> {
                        EncryptedSharedPreferences.create(
                            this@MainActivity, "SecureSecretsContainer", masterKey,
                            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                        ).edit().putString(llave, valor).apply()
                    }
                    2 -> {
                        dataStore.edit { settings -> settings[stringPreferencesKey(llave)] = valor }
                    }
                }
            }

            override suspend fun recuperarDatos(llave: String, metodo: Int): String {
                return when (metodo) {
                    0 -> getSharedPreferences("PlainConfig", Context.MODE_PRIVATE).getString(llave, "Error") ?: "Error"
                    1 -> EncryptedSharedPreferences.create(
                        this@MainActivity, "SecureSecretsContainer", masterKey,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                    ).getString(llave, "Error") ?: "Error"
                    2 -> dataStore.data.first()[stringPreferencesKey(llave)] ?: "Error"
                    else -> "Error"
                }
            }
        }

        // --- ARRANCAMOS LA UI INYECTANDO AMBOS PUENTES ---
        setContent {
            App(puenteSeguridad = puenteSeguridad, puenteBD = puenteBaseDeDatos)
        }
    }
}