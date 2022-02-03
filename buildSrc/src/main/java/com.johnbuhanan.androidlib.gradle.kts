@file:Suppress("DEPRECATION")

plugins {
    id("com.android.library")
    id("com.johnbuhanan.android")
}

dependencies {
    "kapt"(libs.resolveBundle("daggerKapt"))
    "ksp"(libs.resolveDependency("compose.destinations.ksp"))
    "implementation"(libs.resolveDependency("timber"))
    "implementation"(libs.resolveBundle("compose"))
    "implementation"(libs.resolveDependency("compose.destinations.core"))
    "implementation"(libs.resolveBundle("dagger"))
}
