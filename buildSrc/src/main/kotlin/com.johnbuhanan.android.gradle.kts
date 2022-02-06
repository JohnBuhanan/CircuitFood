@file:Suppress("DEPRECATION")

import com.android.build.gradle.BaseExtension

plugins {
    kotlin("android")
}
apply(plugin = "kotlin-kapt")
apply(plugin = "dagger.hilt.android.plugin")
apply(plugin = "com.google.devtools.ksp")

with(the<BaseExtension>()) {
    compileSdkVersion(31)
    buildToolsVersion = "30.0.3"
    defaultConfig {
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packagingOptions {
        excludes += "META-INF/AL2.0"
        excludes += "META-INF/LGPL2.1"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = libs.resolveVersion("compose").requiredVersion
    }
}

dependencies {
    "implementation"(libs.resolveDependency("timber"))

    // Dagger
    "kapt"(libs.resolveBundle("daggerKapt"))
    "implementation"(libs.resolveBundle("dagger"))

    // Compose
    "implementation"(libs.resolveBundle("compose"))

    // Compose Destinations
    "ksp"(libs.resolveDependency("compose.destinations.ksp"))
    "implementation"(libs.resolveDependency("compose.destinations.core"))

    // Coroutines
    "implementation"(libs.resolveDependency("kotlin.coroutines.core"))
    "implementation"(libs.resolveDependency("kotlin.coroutines.android"))

    // Lifecycle
    "implementation"(libs.resolveDependency("androidx.lifecycle.runtime"))
    "implementation"(libs.resolveDependency("androidx.lifecycle.viewmodel"))

    // UnitTest
    "testImplementation"(libs.resolveDependency("junit4"))
    "testImplementation"(libs.resolveDependency("kotlin.coroutines.test"))
    "testImplementation"(libs.resolveDependency("turbine"))

    // Voyager
    "implementation"(libs.resolveBundle("voyager"))

    //  androidTestImplementation libs.androidx.compose.test.junit4
    //  androidTestImplementation libs.androidx.test.junit
    //  androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    //  androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_version"
}
