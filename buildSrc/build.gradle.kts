plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.dagger.hilt.androidGradlePlugin)
    implementation(libs.agp)
    implementation(libs.kotlin.gradle)
    implementation(gradleApi())
    implementation(localGroovy())

    testImplementation(libs.assertk)
    testImplementation(libs.junit4)
}
