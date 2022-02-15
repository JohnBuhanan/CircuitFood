package com.americanexpress.android.testgenerator.screen

import com.americanexpress.android.rxworkflow.screen.TestView
import org.junit.Before
import org.junit.Test

class EditProfileScreenTest {

    private lateinit var view: TestView<EditProfileState, EditProfileCommand, EditProfileEffect>
    private lateinit var screen: EditProfileScreen

    @Before
    fun setUp() {
        view = TestView()
    }

    @Test
    fun `first screen test`() {

    }
}
