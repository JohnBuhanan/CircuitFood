package com.americanexpress.android.testgeneratortestsupport

import com.americanexpress.android.testgenerator.R
import com.americanexpress.android.espresso.EspressoSugar.checkThatStringIsDisplayed

fun onHomeScreen(func: HomeRobot.() -> Unit) = HomeRobot().apply(func)
class HomeRobot {
    fun verifyToolBarTitleDisplayed(toolbarTitle: String) = checkThatStringIsDisplayed(toolbarTitle)
}
