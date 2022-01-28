package com.johnbuhanan.common

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

interface Router {
    fun goBack(): Boolean
    fun goTo(route: Route, builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true }): Boolean
    val routerEvents: Flow<RouterEvent>
}

@Singleton
class RouterImpl @Inject constructor() : Router {
    private val _routerEvents = Channel<RouterEvent>()
    override val routerEvents = _routerEvents.receiveAsFlow()

    override fun goBack(): Boolean = _routerEvents.trySend(RouterEvent.GoBack).isSuccess
    override fun goTo(route: Route, builder: NavOptionsBuilder.() -> Unit): Boolean =
        _routerEvents.trySend(RouterEvent.GoTo(route, builder)).isSuccess
}
