package com.johnbuhanan.common

import androidx.navigation.NavOptionsBuilder

sealed class RouterEvent {
    object GoBack : RouterEvent()
    class GoToFeature(val featureRoute: FeatureRoute, val builder: NavOptionsBuilder.() -> Unit) : RouterEvent()
    class GoToScreen(val screenRoute: ScreenRoute, val builder: NavOptionsBuilder.() -> Unit) : RouterEvent()
    class SynthesizeBackStack(val routes: List<*>, val builder: NavOptionsBuilder.() -> Unit) : RouterEvent()
}
