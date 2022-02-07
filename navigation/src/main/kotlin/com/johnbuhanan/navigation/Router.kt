package com.johnbuhanan.navigation

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

interface Router {
    fun goBack(): Boolean
    fun <T : Route> goTo(route: T, builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true }): Boolean
    val routerEvents: Flow<RouterEvent>
}

@Singleton
class RouterImpl @Inject constructor() : Router {
    private val _routerEvents = Channel<RouterEvent>()
    override val routerEvents = _routerEvents.receiveAsFlow()

    override fun goBack(): Boolean {
        Timber.e("RouterImpl: goBack")
        return _routerEvents.trySend(RouterEvent.GoBack).isSuccess
    }

    override fun <T : Route> goTo(route: T, builder: NavOptionsBuilder.() -> Unit): Boolean {
        Timber.e("RouterImpl: goTo")
        return _routerEvents.trySend(RouterEvent.GoTo(route, builder)).isSuccess
    }
}
