package com.americanexpress.featuregenerator.m1project

import com.americanexpress.featuregenerator.featuremodule.Template
import java.io.File

class ContentUpdater(private val template: Template) {

    fun addLine(
        fileName: String,
        startIndexFunc: (content: String) -> Int,
        endIndexFunc: (content: String) -> Int,
        newLine: String,
        delimiter: String = "\n"
    ) {
        val content = getContent(fileName)

        val startIndex = startIndexFunc(content)
        val endIndex = endIndexFunc(content)

        val templatedLined = template.generateLine(newLine)

        var block = content
            .substring(startIndex, endIndex)
            .split(delimiter)
            .filter { it.trim().isNotEmpty() }
            .toMutableList()

        block = block.apply {
            add(templatedLined)
            sort()
        }

        val updatedBlock = block.joinToString(delimiter)

        val newContent =
            content.substring(0, startIndex) +
                    updatedBlock +
                    content.substring(endIndex)

        updateContent(fileName, newContent)
    }

    private fun getContent(fileName: String): String {
        val file = File("${template.m1ProjectDir}/$fileName")
        return file.readText()
    }

    private fun updateContent(fileName: String, content: String) {
        val file = File("${template.m1ProjectDir}/$fileName")
        file.writeText(content)
    }

}
