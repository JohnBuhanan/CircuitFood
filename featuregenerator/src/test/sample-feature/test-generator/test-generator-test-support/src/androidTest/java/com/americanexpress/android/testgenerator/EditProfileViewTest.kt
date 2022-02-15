package com.americanexpress.android.testgenerator

import com.americanexpress.android.testgenerator.screen.EditProfileEffect
import com.americanexpress.android.testgenerator.screen.EditProfileCommand
import com.americanexpress.android.testgenerator.screen.EditProfileState
import com.americanexpress.android.testgenerator.ui.EditProfileView
//import com.americanexpress.android.testgeneratortestsupport.onEditProfileScreen
import com.americanexpress.android.espresso.EspressoSugarRunner
import com.americanexpress.android.rxworkflow.screen.TestScreen
import com.americanexpress.android.test.uiScenarioRule
import com.americanexpress.android.testing.ViewHostActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EditProfileViewTest {

    @Rule
    @JvmField
    val uiTest = uiScenarioRule<ViewHostActivity>()

    private val testScreen: TestScreen<EditProfileState, EditProfileCommand, EditProfileEffect> = TestScreen()
//    private val state = getInitState()

    @Before
    fun setUp() {
        launchActivity()
        EspressoSugarRunner.waitForIdleSync()
    }

    private fun launchActivity() {
        val scenario = uiTest.launchActivity()
        scenario.onActivity { activity ->
            activity.displayViewSync(
                EditProfileView(context = activity, screen = testScreen)
            )
        }
    }

    @Test
    fun whenArrivingOnScreen_verifyInitialState() {
//        testScreen.screenDataSubject.onNext(state)
//
//        onEditProfileScreen {
//
//        }
    }
}
