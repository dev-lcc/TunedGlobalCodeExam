@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "devlcc.io.tunedglobal.code.exam.common"
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

    // https://stackoverflow.com/a/48988779/11419056
    // * Go to Build, Execution, Deployment > Compiler > Kotlin Compiler BUT Other Settings > Kotlin compiler
    // * Change the Target JVM version to 1.8, then Apply
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
            "-opt-in=kotlin.Experimental",
            "-opt-in=org.koin.core.component.KoinApiExtension"
        )
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Jetbrains Kotlin Dependencies
    implementation(libs.bundles.kotlin)
    implementation(libs.kotlinx.serialization.json)

    // Dependencies for local unit tests
    testImplementation(libs.junit)
    testImplementation(libs.androidx.arch.core.common)
    testImplementation(libs.androidx.arch.core.runtime)
    testImplementation(libs.androidx.arch.core.testing)

    // Coroutine
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)

    // Dependency Injection Framework
    // Koin Dependency Injection
    // Koin for Kotlin
    implementation(libs.koin.core)
    // Koin Test
    testImplementation(libs.koin.test)  // exclude group: 'org.mockito'
}