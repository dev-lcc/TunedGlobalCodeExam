@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApp)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.secrets)
}

android {
    namespace = "devlcc.io.tunedglobal.code.exam"
    buildToolsVersion = libs.versions.gradleBuildTools.get()
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "devlcc.io.tunedglobal.code.exam"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1_000_000
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
        }

        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            // Disables PNG crunching for the release build type.
            isCrunchPngs = true

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

    viewBinding {
        android.buildFeatures.viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        resources.excludes.add("META-INF/main.kotlin_module")
    }

    testOptions {
        unitTests {
            extra.set("includeAndroidResources", true)
            extra.set("returnDefaultValues", true)
        }
    }

    buildFeatures {
        buildConfig = true
    }

    secrets {
        defaultPropertiesFileName = "secrets.defaults.properties"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":core:model"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))

    // Jetbrains Kotlin Dependencies
    implementation(libs.bundles.kotlin)
    implementation(libs.kotlinx.serialization.json)

    // Dependencies for local unit tests
    testImplementation(libs.bundles.unitTest)   // exclude group: 'org.mockito'

    // Android Testing Support Library's runner and rules
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.mockito.android)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.junit.ktx)
    androidTestImplementation(libs.mockk.android)   // exclude module: 'objenesis'

    // Dependency Injection Framework
    // Koin Dependency Injection
    // Koin for Kotlin
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.scope)
    implementation(libs.koin.androidx.viewmodel)
    // Koin Test
    testImplementation(libs.koin.test)  // exclude group: 'org.mockito'

    // AndroidX
    implementation(libs.androidx.core)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    // Material Components
    implementation(libs.android.material.components)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.appcompat.resources)
    implementation(libs.androidx.constraintlayout)

    // Loupe ImageView Helper
    implementation(libs.zoomy)

    // AndroidX Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // AndroidX Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Coroutine
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    // Coroutines Test
    testImplementation(libs.kotlinx.coroutines.test)

    // Coroutine Flow Binding
    implementation(libs.flowbinding.core)
    implementation(libs.flowbinding.android)
    implementation(libs.flowbinding.recyclerview)
    implementation(libs.flowbinding.swiperefreshlayout)

    // Timber Log
    implementation(libs.timber.log)

    // Glide Image Loader
    implementation(libs.glide.image.loader)

    // ViewBindingPropertyDelegate
    implementation(libs.viewbindingpropertydelegate.noreflection)
}