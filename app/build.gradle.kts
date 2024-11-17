plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.example.laboratory12"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.laboratory12"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

secrets {
    // Especifica opcionalmente un nombre de archivo diferente que contenga tus secretos.
    // El complemento predetermina a "local.properties"
    propertiesFileName = "secrets.properties"

    // Un archivo de propiedades que contiene valores secretos predeterminados. Este archivo puede
    // ser registrado en el control de versiones.
    defaultPropertiesFileName = "local.defaults.properties"

    // Configura qué claves deben ser ignoradas por el complemento proporcionando expresiones regulares.
    // "sdk.dir" se ignora por defecto.
    ignoreList.add("keyToIgnore") // Ignora la clave "keyToIgnore"
    ignoreList.add("sdk.*")       // Ignora todas las claves que coincidan con la expresión regular "sdk.*"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}