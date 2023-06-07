@file:Suppress("DEPRECATION")

import com.android.build.gradle.BaseExtension

plugins {
    kotlin("android")
}
apply(plugin = "kotlin-kapt")
apply(plugin = "com.google.devtools.ksp")

with(the<BaseExtension>()) {
    compileSdkVersion(33)
    buildToolsVersion = "30.0.3"
    defaultConfig {
        minSdk = 28
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = libs.resolveVersion("composeCompiler").requiredVersion
    }
}
