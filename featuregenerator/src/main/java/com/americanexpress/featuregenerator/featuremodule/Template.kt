package com.americanexpress.featuregenerator.featuremodule

import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import java.util.Locale

val featureNamePlaceholders = listOf("\${FeatureName}", "\${featureName}", "\${featurename}", "\${feature-name}")
val screenNamePlaceholders = listOf("\${ScreenName}", "\${screen_name}")

const val PLACEHOLDER_INDEX_0 = 0
const val PLACEHOLDER_INDEX_1 = 1
const val PLACEHOLDER_INDEX_2 = 2
const val PLACEHOLDER_INDEX_3 = 3

class Template(
    private val featureName: String,
    val m1ProjectDir: Path = Paths.get("").toAbsolutePath().parent,
    val m1FeatureDir: String = "${m1ProjectDir}/feature/"
) {

    private val featureGeneratorAppDir: String = "$m1ProjectDir/featuregenerator"
    val templatesDir = "$featureGeneratorAppDir/templates/"
    private val templatesFeatureDir = "$templatesDir\${feature-name}"

    fun generateFilePath(templateOutputFilePath: String, screen: String? = null): String {
        var outputPath = templateOutputFilePath

        getFeatureNameMap().forEach { featureNameEntry ->
            outputPath = outputPath.replace(featureNameEntry.key, featureNameEntry.value)
        }

        screen?.let {
            getScreenNameMap(it).forEach { screenNameEntry ->
                outputPath = outputPath.replace(screenNameEntry.key, screenNameEntry.value)
            }
        }

        return outputPath
    }

    fun generateLine(templateLine: String, screen: String? = null): String {
        var outputLine = templateLine

        getFeatureNameMap().forEach { featureNameEntry ->
            outputLine = outputLine.replace(featureNameEntry.key, featureNameEntry.value)
        }

        screen?.let {
            getScreenNameMap(it).forEach { screenNameEntry ->
                outputLine = outputLine.replace(screenNameEntry.key, screenNameEntry.value)
            }
        }

        return outputLine
    }

    fun getFeatureNameMap(): Map<String, String> {
        val nospace = featureName.formatInput()
        return mapOf(
                featureNamePlaceholders[PLACEHOLDER_INDEX_0] to nospace,
                featureNamePlaceholders[PLACEHOLDER_INDEX_1] to nospace.first().toLowerCase() + nospace.substring(1),
                featureNamePlaceholders[PLACEHOLDER_INDEX_2] to nospace.toLowerCase(Locale.ROOT),
                featureNamePlaceholders[PLACEHOLDER_INDEX_3] to featureName.toLowerCase(Locale.ROOT).replace(" ", "-")
        )
    }

    fun getScreenNameMap(screenName: String): Map<String, String> =
        mapOf(
            screenNamePlaceholders[PLACEHOLDER_INDEX_0] to screenName.formatInput(),
            screenNamePlaceholders[PLACEHOLDER_INDEX_1] to screenName.toLowerCase(Locale.ROOT).replace("-", " ").replace(" ", "_")
        )

    // Returns set of template files
    fun getTemplateFiles(): Set<File> = File(templatesFeatureDir).walkTopDown().toSet()
}

fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }
fun String.formatInput(): String = trim().toLowerCase(Locale.ROOT).replace("-", " ").capitalizeWords().replace(" ", "")
