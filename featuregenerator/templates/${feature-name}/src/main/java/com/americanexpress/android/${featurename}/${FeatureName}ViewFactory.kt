package com.americanexpress.android.${featurename}

import android.view.View
import com.americanexpress.android.rxworkflow.screen.Screen
import com.americanexpress.android.rxworkflow.view.viewfactory.ViewFactory
import com.americanexpress.android.${featurename}.screen.${EachScreen}Screen
import com.americanexpress.android.${featurename}.ui.${EachScreen}View

internal val ${featureName}ViewFactory = ${FeatureName}ViewFactory

object ${FeatureName}ViewFactory : ViewFactory<Screen<*, *, *>> {

    override fun viewForScreen(screen: Screen<*, *,*>, parent: View): View? {
        return when (screen) {
            is ${EachScreen}Screen -> ${EachScreen}View(parent.context, screen)
            else -> null
        }
    }
}
