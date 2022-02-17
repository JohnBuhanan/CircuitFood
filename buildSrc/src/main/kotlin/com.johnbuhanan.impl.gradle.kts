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
    "implementation"(project(":common:ui"))

    // Image loading
    "implementation"(libs.resolveDependency("coil"))
}
