@file:Suppress("DEPRECATION")

plugins {
    id("com.android.library")
    id("com.johnbuhanan.android")
    id("com.johnbuhanan.dependencies")
}

dependencies {
    "ksp"(libs.resolveDependency("moshiCodeGen"))

    "implementation"(project(":common:coroutines"))

    // Retrofit
    "implementation"(libs.resolveDependency("retrofit"))
    "implementation"(libs.resolveDependency("retrofit.converter.moshi"))
    "implementation"(libs.resolveDependency("moshi"))
}
