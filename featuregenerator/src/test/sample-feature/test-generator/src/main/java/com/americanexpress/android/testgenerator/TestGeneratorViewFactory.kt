package com.americanexpress.android.testgenerator

import android.view.View
import com.americanexpress.android.rxworkflow.screen.Screen
import com.americanexpress.android.rxworkflow.view.viewfactory.ViewFactory
import com.americanexpress.android.testgenerator.screen.HomeScreen
import com.americanexpress.android.testgenerator.screen.EditProfileScreen
import com.americanexpress.android.testgenerator.ui.HomeView
import com.americanexpress.android.testgenerator.ui.EditProfileView

internal val testGeneratorViewFactory = TestGeneratorViewFactory

object TestGeneratorViewFactory : ViewFactory<Screen<*, *, *>> {

    override fun viewForScreen(screen: Screen<*, *,*>, parent: View): View? {
        return when (screen) {
            is HomeScreen -> HomeView(parent.context, screen)
            is EditProfileScreen -> EditProfileView(parent.context, screen)
            else -> null
        }
    }
}
