package com.americanexpress.android.testgeneratortestsupport

import com.americanexpress.android.testgenerator.R
import com.americanexpress.android.espresso.EspressoSugar.checkThatStringIsDisplayed

fun onEditProfileScreen(func: EditProfileRobot.() -> Unit) = EditProfileRobot().apply(func)
class EditProfileRobot {
    fun verifyToolBarTitleDisplayed(toolbarTitle: String) = checkThatStringIsDisplayed(toolbarTitle)
}
