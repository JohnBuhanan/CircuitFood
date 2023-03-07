plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

dependencies {
    implementation(libs.dagger.hilt.androidGradlePlugin)
    implementation(libs.androidGradlePlugin)
    implementation(libs.kotlin.gradle)
    implementation(gradleApi())
    implementation(localGroovy())
}
