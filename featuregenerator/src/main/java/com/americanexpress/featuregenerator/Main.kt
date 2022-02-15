@file:JvmName("Main")

package com.americanexpress.featuregenerator

import com.americanexpress.featuregenerator.featuremodule.FeatureGenerator
import com.americanexpress.featuregenerator.featuremodule.Template
import kotlin.system.exitProcess

const val FEATURE_NAME_RECOMMENDED = "\n* Recommended Feature Name Input Format - Single Word: Profile | Multi Word: Card Replacement *"
const val ENTER_FEATURE_NAME_PROMPT = "Enter Feature Name : "
const val ENTER_SCREEN_COUNT_PROMPT = "How many screens does this feature have? "
const val SCREEN_NAME_RECOMMENDED = "\n* Recommended Screen Name Input Format - Single Word: Home | Multi Word: Order Review *\n"
const val ENTER_SCREEN_NAME_PROMPT = "Enter Screen Name"
const val INVALID_INPUT_EMPTY = "Invalid Input - Empty"
const val INVALID_INPUT_ZERO_SCREEN_COUNT = "Invalid Input - Screen count must be a number > 0"
const val INVALID_INPUT_NON_NUMBER_SCREEN_COUNT = "Invalid Input - Screen count must be a number"

fun main() {
    val screenList = mutableListOf<String>()

    println(FEATURE_NAME_RECOMMENDED)
    print(ENTER_FEATURE_NAME_PROMPT)
    val featureNameInput = readLine()
    if (featureNameInput.isNullOrEmpty()) {
        print(INVALID_INPUT_EMPTY)
        exitProcess(1)
    }

    print(ENTER_SCREEN_COUNT_PROMPT)
    val screenCountInput = readLine()
    if (screenCountInput.isNullOrEmpty()) {
        print(INVALID_INPUT_EMPTY)
        exitProcess(1)
    }

    val screenCount = screenCountInput.toIntOrNull() ?: run {
        print(INVALID_INPUT_NON_NUMBER_SCREEN_COUNT)
        exitProcess(1)
    }

    if (screenCount < 1) {
        print(INVALID_INPUT_ZERO_SCREEN_COUNT)
        exitProcess(1)
    }

    print(SCREEN_NAME_RECOMMENDED)
    for (x in 1..screenCount) {

        print("$ENTER_SCREEN_NAME_PROMPT $x : ")
        val screenNameInput = readLine()
        if (screenNameInput.isNullOrEmpty()) {
            print(INVALID_INPUT_EMPTY)
            exitProcess(1)
        }

        screenList.add(screenNameInput)
    }

    val template = Template(featureNameInput)

    with(template) {
        FeatureGenerator(this, screenList).generate()
    }
}
