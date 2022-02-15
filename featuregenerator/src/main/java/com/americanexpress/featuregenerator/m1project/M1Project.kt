package com.americanexpress.featuregenerator.m1project

import com.americanexpress.featuregenerator.featuremodule.Template

const val SETTINGS_GRADLE_FILE_NAME = "/settings.gradle.kts"
const val APP_GRADLE_FILE_NAME = "/app/app.gradle"

const val APP_SRC = "/app/src"
const val JAVA_INIT = "/java/com/americanexpress/android/intl/app/dagger/initialization"

const val DAGGER_DIR = "$APP_SRC/main$JAVA_INIT"
const val TEST_DAGGER_DIR = "$APP_SRC/androidTest$JAVA_INIT"

const val DAGGER_BINDINGS_PATH = "$DAGGER_DIR/InitializedAppDaggerBindings.kt"
const val INITIALIZED_COMPONENT_PATH = "$DAGGER_DIR/InitializedComponent.kt"
const val TEST_INITIALIZED_COMPONENT_PATH = "$TEST_DAGGER_DIR/TestInitializedComponent.kt"

/*
settings.gradle
app.gradle
InitializedAppDaggerBindings.kt
InitializedComponent.kt
TestInitializedComponent.kt
*/

class M1Project(template: Template) {

    private val contentUpdater = ContentUpdater(template)

    fun update(
        settingsGradlePath: String = SETTINGS_GRADLE_FILE_NAME,
        appGradlePath: String = APP_GRADLE_FILE_NAME,
        initializedAppDaggerBindingsPath: String = DAGGER_BINDINGS_PATH,
        initializedComponentPath: String = INITIALIZED_COMPONENT_PATH,
        testInitializedComponentPath: String = TEST_INITIALIZED_COMPONENT_PATH
    ) {
        updateSettingsGradle(settingsGradlePath)
        updateAppGradle(appGradlePath)
        updateInitializedAppDaggerBindings(initializedAppDaggerBindingsPath)
        updateInitializedComponent(initializedComponentPath)
        updateTestInitializedComponent(testInitializedComponentPath)
    }

    private fun updateSettingsGradle(settingsGradlePath: String) {
        contentUpdater.addLine(
            fileName = settingsGradlePath,
            startIndexFunc = { it.indexOf("include(") + 8 },
            endIndexFunc = { it.indexOf(")", startIndex = it.indexOf("include(")) - 1 },
            newLine = "\":feature:\${feature-name}\"",
            delimiter = ",\n    "
        )
        contentUpdater.addLine(
            fileName = settingsGradlePath,
            startIndexFunc = { it.indexOf("include(") + 8 },
            endIndexFunc = { it.indexOf(")", startIndex = it.indexOf("include(")) - 1 },
            newLine = "\":feature:\${feature-name}:\${feature-name}-test-support\"",
            delimiter = ",\n    "
        )
    }

    private fun updateAppGradle(appGradlePath: String) {
        val startIndexFuncImplementation: (content: String) -> Int = { it.indexOf("  implementation") }
        contentUpdater.addLine(
            fileName = appGradlePath,
            startIndexFunc = startIndexFuncImplementation,
            endIndexFunc = { it.indexOf("  if (experimentalMode) {", startIndexFuncImplementation(it)) - 2 },
            newLine = "  implementation project(':feature:\${feature-name}')"
        )

        val startIndexFuncAndroidTestImplementation: (content: String) -> Int = { it.indexOf("  androidTestImplementation") }
        contentUpdater.addLine(
            fileName = appGradlePath,
            startIndexFunc = startIndexFuncAndroidTestImplementation,
            endIndexFunc = { it.indexOf("  androidTestImplementation") },
            newLine = "  androidTestImplementation project(':feature:\${feature-name}:\${feature-name}-test-support')\n"
        )
    }

    private fun updateInitializedAppDaggerBindings(initializedAppDaggerBindingsPath: String) {
        contentUpdater.addLine(
            fileName = initializedAppDaggerBindingsPath,
            startIndexFunc = { it.indexOf("import") },
            endIndexFunc = { it.indexOf("interface InitializedAppDaggerBindings {") - 2 },
            newLine = "import com.americanexpress.android.\${featurename}.\${FeatureName}Activity"
        )

        contentUpdater.addLine(
            fileName = initializedAppDaggerBindingsPath,
            startIndexFunc = { it.indexOf("    fun inject") },
            endIndexFunc = { it.indexOf("}") - 1 },
            newLine = "    fun inject(activity: \${FeatureName}Activity)"
        )

        val startIndexFunc: (content: String) -> Int = { it.indexOf("    bind(") }
        contentUpdater.addLine(
            fileName = initializedAppDaggerBindingsPath,
            startIndexFunc = startIndexFunc,
            endIndexFunc = { it.indexOf("\n}", startIndexFunc(it)) },
            newLine = "    bind(\${FeatureName}Activity::class) { bindings.inject(it) }"
        )
    }

    private fun updateInitializedComponent(initializedComponentPath: String) = updateComponent(initializedComponentPath)
    private fun updateTestInitializedComponent(testInitializedComponentPath: String) = updateComponent(testInitializedComponentPath)

    private fun updateComponent(componentPath: String) {
        contentUpdater.addLine(
            fileName = componentPath,
            startIndexFunc = { it.indexOf("import") },
            endIndexFunc = { it.indexOf("@InitializedScope") - 2 },
            newLine = "import com.americanexpress.android.\${featurename}.\${FeatureName}Module"
        )

        contentUpdater.addLine(
            fileName = componentPath,
            startIndexFunc = { it.indexOf("[") + 2 },
            endIndexFunc = { it.indexOf("    ]") - 1 },
            newLine = "        \${FeatureName}Module::class",
            delimiter = ",\n"
        )
    }
}
