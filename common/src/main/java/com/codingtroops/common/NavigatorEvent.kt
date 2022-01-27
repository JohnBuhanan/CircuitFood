package com.codingtroops.common

import androidx.navigation.NavOptionsBuilder
import com.ramcosta.composedestinations.spec.Direction

sealed class NavigatorEvent {
    object NavigateUp : NavigatorEvent()
    class Directions(val destination: Direction, val builder: NavOptionsBuilder.() -> Unit) : NavigatorEvent()
}
