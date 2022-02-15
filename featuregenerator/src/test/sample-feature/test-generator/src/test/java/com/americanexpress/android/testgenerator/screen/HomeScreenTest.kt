package com.americanexpress.android.testgenerator.screen

import com.americanexpress.android.rxworkflow.screen.TestView
import org.junit.Before
import org.junit.Test

class HomeScreenTest {

    private lateinit var view: TestView<HomeState, HomeCommand, HomeEffect>
    private lateinit var screen: HomeScreen

    @Before
    fun setUp() {
        view = TestView()
    }

    @Test
    fun `first screen test`() {

    }
}
