import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    androidLibrary {
       namespace = "ec.edu.epn.aplicacion.shared"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()
    
       compilerOptions {
           jvmTarget = JvmTarget.JVM_11
       }
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
    }

    sourceSets {
        commonMain.dependencies {
            // El framework visual base
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3) // Usamos Material 3 para switches, inputs y botones modernos

            // 🌐 MÓDULO 1: RED (Ktor para comunicación HTTP asíncrona)
            implementation("io.ktor:ktor-client-core:2.3.0")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.0")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.0")

            // 🗄️ MÓDULO 2: NOSQL (Realm Local para persistencia ágil)
            implementation("io.realm.kotlin:library-base:1.11.0")
        }

        androidMain.dependencies {
            implementation("io.ktor:ktor-client-okhttp:2.3.0")
            // 🔒 MÓDULO 3: SEGURIDAD (Acceso a las APIs criptográficas nativas de Android)
            implementation("androidx.security:security-crypto-ktx:1.1.0-alpha06")
            implementation("androidx.datastore:datastore-preferences:1.0.0")
// MÓDULO 3: SEGURIDAD (Crypto)
            implementation("androidx.security:security-crypto-ktx:1.1.0-alpha06")

            // MÓDULO 3: DATASTORE (Preferencias modernas)
            implementation("androidx.datastore:datastore-preferences:1.0.0")

            // (Aquí también debe estar tu motor de red que pusimos antes)
            implementation("io.ktor:ktor-client-okhttp:2.3.0")
            // 🔒 LIBRERÍAS DE HARDWARE NATIVO:
            implementation("androidx.security:security-crypto-ktx:1.1.0-alpha06")
            implementation("androidx.datastore:datastore-preferences:1.0.0")
            implementation("androidx.datastore:datastore-preferences-core:1.0.0") // <-- Inyectamos el core directamente
        }

    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}