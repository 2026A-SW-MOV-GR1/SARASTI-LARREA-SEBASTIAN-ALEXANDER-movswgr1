package ec.edu.epn.aplicacion

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform