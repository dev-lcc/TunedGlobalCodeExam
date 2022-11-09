// Top-level build file where you can add configuration options common to all sub-projects/modules.
// https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApp).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinKapt).apply(false)
    alias(libs.plugins.kotlinParcelize).apply(false)
    alias(libs.plugins.kotlinSerialization).apply(false)
    alias(libs.plugins.secrets).apply(false)
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}