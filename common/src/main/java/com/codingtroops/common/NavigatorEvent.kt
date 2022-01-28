package com.codingtroops.common

import androidx.navigation.NavOptionsBuilder
import com.ramcosta.composedestinations.spec.Direction

sealed class NavigatorEvent {
    object NavigateUp : NavigatorEvent()
    class Directions(val route: Direction, val builder: NavOptionsBuilder.() -> Unit) : NavigatorEvent()
}
