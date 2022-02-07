package com.johnbuhanan.navigation

import androidx.navigation.NavOptionsBuilder

sealed class RouterEvent {
    object GoBack : RouterEvent()
    class GoTo(val route: Route, val builder: NavOptionsBuilder.() -> Unit) : RouterEvent()
}
