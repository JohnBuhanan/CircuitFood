@file:Suppress("DEPRECATION")

plugins {
    id("com.android.library")
    id("com.johnbuhanan.android")
    id("com.johnbuhanan.dependencies")
}

dependencies {
    "implementation"(project(":common"))
    "implementation"(project(":navigation"))

    // Retrofit
    "implementation"(libs.resolveDependency("retrofit"))
    "implementation"(libs.resolveDependency("retrofit.converter.gson"))
    "implementation"(libs.resolveDependency("gson"))

    // Image loading
    "implementation"(libs.resolveDependency("coil"))
}
