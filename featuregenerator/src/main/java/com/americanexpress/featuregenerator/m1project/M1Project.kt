package com.americanexpress.featuregenerator.m1project

import com.americanexpress.featuregenerator.featuremodule.Template

const val SETTINGS_GRADLE_FILE_NAME = "/settings.gradle"
const val APP_GRADLE_FILE_NAME = "/app/app.gradle"

const val APP_SRC = "/app/src"
const val JAVA_INIT = "/java/com/americanexpress/android/intl/app/dagger/initialization"

/*
settings.gradle
app.gradle
*/

class M1Project(template: Template) {

    private val contentUpdater = ContentUpdater(template)

    fun update(
        settingsGradlePath: String = SETTINGS_GRADLE_FILE_NAME,
        appGradlePath: String = APP_GRADLE_FILE_NAME,
    ) {
        updateSettingsGradle(settingsGradlePath)
//        updateAppGradle(appGradlePath)
    }

    private fun updateSettingsGradle(settingsGradlePath: String) {
        contentUpdater.addLine(
            fileName = settingsGradlePath,
            startIndexFunc = { it.indexOf("include(") + 8 },
            endIndexFunc = { it.indexOf(")", startIndex = it.indexOf("include(")) - 1 },
            newLine = """":features:${'$'}{feature-name}:api", ":features:${'$'}{feature-name}:impl", ":features:${'$'}{feature-name}:impl:domain"""".trimMargin(),
            delimiter = ",\n    "
        )
//        contentUpdater.addLine(
//            fileName = settingsGradlePath,
//            startIndexFunc = { it.indexOf("include(") + 8 },
//            endIndexFunc = { it.indexOf(")", startIndex = it.indexOf("include(")) - 1 },
//            newLine = "\":feature:\${feature-name}:\${feature-name}-test-support\"",
//            delimiter = ",\n    "
//        )
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
}
