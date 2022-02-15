plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.21"

    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.25")
}

application {
    mainClass.set("com.americanexpress.featuregenerator.Main")
}
