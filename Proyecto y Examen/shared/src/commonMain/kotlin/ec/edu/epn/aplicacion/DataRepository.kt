package ec.edu.epn.aplicacion

interface DataRepository {
    fun saveItem(item: String)
    fun getAllItems(): List<String>
}

class SqlRepository : DataRepository {
    override fun saveItem(item: String) {
        println("INFO: [SQL_ENGINE] Ejecutando inserción estructurada en SQLite local para el valor: $item")
    }

    override fun getAllItems(): List<String> {
        // AQUÍ ESTÁ LA MAGIA PARA EL VIDEO
        println("INFO: [SQL_ENGINE] Leyendo registros desde tabla estructurada SQLite")
        return listOf("Registro SQLite Alfa", "Registro SQLite Beta")
    }
}

class NoSqlRepository : DataRepository {
    override fun saveItem(item: String) {
        println("DEBUG: [NOSQL_ENGINE] Persistiendo documento JSON dinámico en Realm Local: { content: $item }")
    }

    override fun getAllItems(): List<String> {
        // AQUÍ ESTÁ LA MAGIA PARA EL VIDEO
        println("DEBUG: [NOSQL_ENGINE] Leyendo documentos dinámicos desde Realm")
        return listOf("Documento Realm NoSQL-01", "Documento Realm NoSQL-02")
    }
}