@file:Suppress("DEPRECATION")

plugins {
    id("com.android.library")
    id("com.johnbuhanan.android")
    id("com.johnbuhanan.dependencies")
}

dependencies {
    "implementation"(project(":common:coroutines"))
    "implementation"(project(":common:navigation"))
    "implementation"(project(":common:viewmodel"))

    // Retrofit
    "implementation"(libs.resolveDependency("retrofit"))
    "implementation"(libs.resolveDependency("retrofit.converter.gson"))
    "implementation"(libs.resolveDependency("gson"))

    // Image loading
    "implementation"(libs.resolveDependency("coil"))
}
