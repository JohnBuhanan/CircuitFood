package com.americanexpress.android.testgenerator

import com.americanexpress.android.testgenerator.screen.HomeEffect
import com.americanexpress.android.testgenerator.screen.HomeCommand
import com.americanexpress.android.testgenerator.screen.HomeState
import com.americanexpress.android.testgenerator.ui.HomeView
//import com.americanexpress.android.testgeneratortestsupport.onHomeScreen
import com.americanexpress.android.espresso.EspressoSugarRunner
import com.americanexpress.android.rxworkflow.screen.TestScreen
import com.americanexpress.android.test.uiScenarioRule
import com.americanexpress.android.testing.ViewHostActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewTest {

    @Rule
    @JvmField
    val uiTest = uiScenarioRule<ViewHostActivity>()

    private val testScreen: TestScreen<HomeState, HomeCommand, HomeEffect> = TestScreen()
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
                HomeView(context = activity, screen = testScreen)
            )
        }
    }

    @Test
    fun whenArrivingOnScreen_verifyInitialState() {
//        testScreen.screenDataSubject.onNext(state)
//
//        onHomeScreen {
//
//        }
    }
}
