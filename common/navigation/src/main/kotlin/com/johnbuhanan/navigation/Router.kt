package com.johnbuhanan.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

interface Router {
    fun pop(): Boolean
    fun push(vararg routes: Route): Boolean
    fun pushBottomSheet(route: Route): Boolean
    val routerEvents: Flow<RouterEvent>
}

@Singleton
class RouterImpl @Inject constructor() : Router {
    private val _routerEvents = Channel<RouterEvent>()
    override val routerEvents = _routerEvents.receiveAsFlow()

    override fun pop(): Boolean {
        return _routerEvents.trySend(RouterEvent.Pop).isSuccess
    }

    override fun push(vararg routes: Route): Boolean {
        return _routerEvents.trySend(RouterEvent.Push(routes)).isSuccess
    }

    override fun pushBottomSheet(route: Route): Boolean {
        return _routerEvents.trySend(RouterEvent.PushBottomSheet(route)).isSuccess
    }
}
