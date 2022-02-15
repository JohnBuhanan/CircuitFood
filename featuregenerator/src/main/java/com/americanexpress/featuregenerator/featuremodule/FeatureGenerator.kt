package com.americanexpress.featuregenerator.featuremodule

import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import kotlin.text.Charsets.UTF_8

const val EACH_SCREEN_PLACEHOLDER = "\${EachScreen}"

class FeatureGenerator(private val template: Template, private val screenList: List<String>) {

    fun generate() {
        template.getTemplateFiles().forEach { generateFile(it) }

        val featureModuleName = template.getFeatureNameMap()[featureNamePlaceholders[PLACEHOLDER_INDEX_3]]
        println("Your new feature is generated at `/features/$featureModuleName`! Please sync your project.")
    }

    private fun generateFile(templateFile: File) {
        val path = templateFile.absolutePath
        val relative: String = File(template.templatesDir).toURI().relativize(File(path).toURI()).path

        val templatedOutputFilePath = template.m1FeatureDir + relative

        if(templateFile.isScreenTemplate()) {
            screenList.forEach { screenName ->
                createFile(templateFile = templateFile, screen = screenName, templatedOutputFilePath = templatedOutputFilePath)
            }
        } else {
            createFile(templateFile = templateFile, templatedOutputFilePath = templatedOutputFilePath)
        }
    }

    private fun createFile(templateFile: File, screen: String? = null, templatedOutputFilePath: String) {
        val outputPath = template.generateFilePath(templatedOutputFilePath, screen)
        val outputFile = File(outputPath)

        if (templateFile.isFile) {
            outputFile.createNewFile()
            writeContent(outputFile, templateFile, screen)
        } else {
            outputFile.mkdirs()
        }
    }


    private fun writeContent(file: File, templateFile: File, screen: String? = null) {
        FileOutputStream(file).use { fileOutstream ->
            OutputStreamWriter(fileOutstream, UTF_8).use { outputStreamWriter ->
                BufferedWriter(outputStreamWriter).use { bufferedWriter ->
                    val templateLines = templateFile.readLines() // List of lines for template file
                    writeLine(templateLines, screen, bufferedWriter)
                    fileOutstream.fd.sync()
                }
            }
        }
    }


    private fun writeLine(templateLines: List<String>, screen: String? = null, bufferedWriter: BufferedWriter) {
        templateLines.forEach { templateLine ->
            val outputLine = template.generateLine(templateLine, screen)

            if(EACH_SCREEN_PLACEHOLDER in outputLine) {
                screenList.forEach { eachScreen ->
                    applyEachScreen(outputLine, eachScreen, bufferedWriter)
                }
            } else {
                bufferedWriter.apply {
                    write(outputLine)
                    newLine()
                }
            }
        }
    }

    private fun applyEachScreen(line: String, screen: String, bufferedWriter: BufferedWriter) {
        bufferedWriter.apply {
            write(line.replace(EACH_SCREEN_PLACEHOLDER, screen.formatInput()))
            newLine()
        }
    }
}

fun File.isScreenTemplate()= screenNamePlaceholders.any { it in this.name }
