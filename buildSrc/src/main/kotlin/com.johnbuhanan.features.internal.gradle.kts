@file:Suppress("DEPRECATION")

plugins {
    id("com.android.library")
    id("com.johnbuhanan.android")
    id("com.johnbuhanan.dependencies")
}

dependencies {
    "implementation"(project(":common:coroutines"))
    "implementation"(project(":common:di"))
    "implementation"(project(":common:ui"))

    // Image loading
    "implementation"(libs.resolveDependency("coil"))
}
