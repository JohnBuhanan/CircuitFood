package com.johnbuhanan.navigation

sealed class RouterEvent {
    object Pop : RouterEvent()
    class Push(val routes: Array<out Route>) : RouterEvent()
    class PushBottomSheet(val route: Route) : RouterEvent()
}
