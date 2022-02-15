package com.americanexpress.android.${featurename}

import com.americanexpress.android.${featurename}.screen.${ScreenName}Effect
import com.americanexpress.android.${featurename}.screen.${ScreenName}Command
import com.americanexpress.android.${featurename}.screen.${ScreenName}State
import com.americanexpress.android.${featurename}.ui.${ScreenName}View
//import com.americanexpress.android.${featurename}testsupport.on${ScreenName}Screen
import com.americanexpress.android.espresso.EspressoSugarRunner
import com.americanexpress.android.rxworkflow.screen.TestScreen
import com.americanexpress.android.test.uiScenarioRule
import com.americanexpress.android.testing.ViewHostActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ${ScreenName}ViewTest {

    @Rule
    @JvmField
    val uiTest = uiScenarioRule<ViewHostActivity>()

    private val testScreen: TestScreen<${ScreenName}State, ${ScreenName}Command, ${ScreenName}Effect> = TestScreen()
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
                ${ScreenName}View(context = activity, screen = testScreen)
            )
        }
    }

    @Test
    fun whenArrivingOnScreen_verifyInitialState() {
//        testScreen.screenDataSubject.onNext(state)
//
//        on${ScreenName}Screen {
//
//        }
    }
}
