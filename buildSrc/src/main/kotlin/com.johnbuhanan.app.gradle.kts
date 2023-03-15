@file:Suppress("DEPRECATION")

plugins {
    id("com.android.application")
    id("com.johnbuhanan.android")
    id("com.johnbuhanan.dependencies")
}

dependencies {
    "implementation"(project(":common:di"))
}