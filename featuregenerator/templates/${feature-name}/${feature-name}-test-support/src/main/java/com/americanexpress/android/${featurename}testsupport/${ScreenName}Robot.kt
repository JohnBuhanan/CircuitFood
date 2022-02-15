package com.americanexpress.android.${featurename}testsupport

import com.americanexpress.android.${featurename}.R
import com.americanexpress.android.espresso.EspressoSugar.checkThatStringIsDisplayed

fun on${ScreenName}Screen(func: ${ScreenName}Robot.() -> Unit) = ${ScreenName}Robot().apply(func)
class ${ScreenName}Robot {
    fun verifyToolBarTitleDisplayed(toolbarTitle: String) = checkThatStringIsDisplayed(toolbarTitle)
}
