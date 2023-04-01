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
//    "testImplementation"(libs.resolveDependency("testing.hamcrest"))
//    "testImplementation"(libs.resolveDependency("androidx.compose.ui.testing.junit"))
//    "testImplementation"(libs.resolveDependency("molecule.runtime"))
//    "implementation"(libs.resolveDependency("androidx.tracing"))
//    "testImplementation"(libs.resolveDependency("androidx.loader"))
//    "testImplementation"(libs.resolveDependency("testing.espresso.core"))
//    "testImplementation"(libs.resolveDependency("androidx.compose.ui.testing.manifest"))

}
