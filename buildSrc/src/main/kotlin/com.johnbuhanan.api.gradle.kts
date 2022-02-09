@file:Suppress("DEPRECATION")

plugins {
    id("com.android.library")
}
apply(plugin = "com.johnbuhanan.android")

dependencies {
    "implementation"(libs.resolveDependency("voyagerNavigator"))
    "implementation"(libs.resolveDependency("androidx.compose.ui"))

}
