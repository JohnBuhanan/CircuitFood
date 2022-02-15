import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.kotlin

plugins {
    kotlin("android")
}
apply(plugin = "kotlin-kapt")
apply(plugin = "dagger.hilt.android.plugin")
apply(plugin = "com.google.devtools.ksp")

dependencies {
    "implementation"(libs.resolveDependency("timber"))
    "implementation"(libs.resolveDependency("kotlinReflect"))

    // Dagger
    "kapt"(libs.resolveBundle("daggerKapt"))
    "implementation"(libs.resolveBundle("dagger"))

    // Compose
    "implementation"(libs.resolveBundle("compose"))

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
    "testImplementation"(libs.resolveDependency("assertk"))

    // Voyager
    "implementation"(libs.resolveBundle("voyager"))

    //  androidTestImplementation libs.androidx.compose.test.junit4
    //  androidTestImplementation libs.androidx.test.junit
    //  androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    //  androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_version"
}
