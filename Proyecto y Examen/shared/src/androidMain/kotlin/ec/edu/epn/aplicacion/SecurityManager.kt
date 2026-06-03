package ec.edu.epn.aplicacion

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecurityManager(private val context: Context) {

    // 1. SharedPreferences tradicionales: Almacenamiento clave-valor plano en XML directo (Sin Encriptación)
    fun savePlainSecret(key: String, value: String) {
        val prefs = context.getSharedPreferences("PlainConfig", Context.MODE_PRIVATE)
        prefs.edit().putString(key, value).apply()
    }

    // 2. EncryptedSharedPreferences: Cifrado automático sobre disco con AES-256 SIV y AES-128 GCM
    fun saveEncryptedSecret(key: String, value: String) {
        // Le solicitamos al contenedor seguro de Android que construya o recupere una llave maestra cifrada
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        // Inicializamos las preferencias seguras asociándolas a la llave maestra de hardware
        val encryptedPrefs = EncryptedSharedPreferences.create(
            context,
            "SecureSecretsContainer",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, // Cifra el nombre de la clave
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // Cifra el valor del secreto
        )
        encryptedPrefs.edit().putString(key, value).apply()
    }

    // Comportamiento de recuperación bajo conocimiento previo de la llave
    fun getSecret(key: String, useEncryption: Boolean): String {
        return if (useEncryption) {
            val masterKey = MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
            val encryptedPrefs = EncryptedSharedPreferences.create(
                context, "SecureSecretsContainer", masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
            // Si la llave no existe, retorna un aviso genérico sin revelar información
            encryptedPrefs.getString(key, "Error: El secreto no fue encontrado en este compartimento.")
                ?: "Error: El secreto no fue encontrado."
        } else {
            val prefs = context.getSharedPreferences("PlainConfig", Context.MODE_PRIVATE)
            prefs.getString(key, "Error: El secreto no fue encontrado en este compartimento.")
                ?: "Error: El secreto no fue encontrado."
        }
    }
}