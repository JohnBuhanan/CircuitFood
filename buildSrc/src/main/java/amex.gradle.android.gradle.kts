@file:Suppress("DEPRECATION")

import com.android.build.gradle.BaseExtension

plugins {
    kotlin("android")
}

with(the<BaseExtension>()) {
    compileSdkVersion(31)

    defaultConfig {
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }

    packagingOptions {
        excludes += "META-INF/AL2.0"
        excludes += "META-INF/LGPL2.1"
    }
}

dependencies {
//    "implementation"(libs.resolveDependency("busybee.android"))
}
