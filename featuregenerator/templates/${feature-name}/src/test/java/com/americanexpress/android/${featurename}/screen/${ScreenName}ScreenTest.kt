package com.americanexpress.android.${featurename}.screen

import com.americanexpress.android.rxworkflow.screen.TestView
import org.junit.Before
import org.junit.Test

class ${ScreenName}ScreenTest {

    private lateinit var view: TestView<${ScreenName}State, ${ScreenName}Command, ${ScreenName}Effect>
    private lateinit var screen: ${ScreenName}Screen

    @Before
    fun setUp() {
        view = TestView()
    }

    @Test
    fun `first screen test`() {

    }
}
