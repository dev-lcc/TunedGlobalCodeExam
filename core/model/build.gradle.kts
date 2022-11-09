@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinParcelize)
}

android {
    namespace = "devlcc.io.tunedglobal.code.exam.model"
    buildToolsVersion = libs.versions.gradleBuildTools.get()
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            //
            // By default, the 'debug' build type enables debugging options
            // and signs your APK with a generic debug keystore.
            //
            /*isDebuggable = true*/
            isMinifyEnabled = false
        }

        getByName("release") {
            //
            // The 'release' build type strips out debug symbols
            // and requires you to create a release key and keystore for your app.
            //
            /*isDebuggable = false*/
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Jetbrains Kotlin Dependencies
    implementation(libs.bundles.kotlin)

    // Dependencies for local unit tests
    testImplementation(libs.bundles.unitTest)   // exclude group: 'org.mockito'
}