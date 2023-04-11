@file:Suppress("DEPRECATION")

plugins {
    id("com.android.library")
    id("com.johnbuhanan.android")
    id("com.johnbuhanan.dependencies")
}

dependencies {
    "implementation"(project(":common:coroutines"))
    "implementation"(project(":common:di"))
    "implementation"(project(":common:resultlistener"))
    "implementation"(project(":common:ui"))

    // Image loading
    "implementation"(libs.resolveDependency("coil"))

    "testImplementation"(libs.resolveDependency("circuitTest"))
    "testImplementation"(libs.resolveDependency("robolectric"))

    "androidTestImplementation"(libs.resolveDependency("androidx.compose.test.junit4"))
    "androidTestImplementation"(libs.resolveDependency("leakcanary.android.instrumentation"))
    "androidTestImplementation"(libs.resolveDependency("truth"))
    "androidTestImplementation"(libs.resolveDependency("androidx.compose.ui.testing.manifest"))
}
