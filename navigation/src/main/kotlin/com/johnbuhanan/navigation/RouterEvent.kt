package com.johnbuhanan.navigation

import androidx.navigation.NavOptionsBuilder

sealed class RouterEvent {
    object GoBack : RouterEvent()
    class GoTo<T : Route>(val route: T, val builder: NavOptionsBuilder.() -> Unit) : RouterEvent()
}
