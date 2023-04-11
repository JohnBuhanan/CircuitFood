@file:Suppress("DEPRECATION")

plugins {
    id("com.android.library")
}
apply(plugin = "com.johnbuhanan.android")
apply(plugin = "kotlin-parcelize")

dependencies {
    "implementation"(libs.resolveDependency("androidx.compose.ui"))
    "implementation"(libs.resolveDependency("circuitRuntime"))
}
