package com.johnbuhanan.navigation

import androidx.lifecycle.SavedStateHandle
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

interface Router {
    fun pop(): Boolean
    fun push(vararg routes: Route): Boolean
    fun pushBottomSheet(route: Route): Boolean
    val routerEvents: Flow<RouterEvent>
    val savedStateHandle: SavedStateHandle
}

@Singleton
class RouterImpl @Inject constructor() : Router {
    private val _routerEvents = Channel<RouterEvent>()
    override val routerEvents = _routerEvents.receiveAsFlow()
    override val savedStateHandle = SavedStateHandle()

    override fun pop(): Boolean {
        return _routerEvents.trySend(RouterEvent.Pop).isSuccess
    }

    override fun push(vararg routes: Route): Boolean {
        routes.forEach {
            savedStateHandle.set(it::class.java.name, it)
        }

        return _routerEvents.trySend(RouterEvent.Push(routes)).isSuccess
    }

    override fun pushBottomSheet(route: Route): Boolean {
        return _routerEvents.trySend(RouterEvent.PushBottomSheet(route)).isSuccess
    }
}

inline fun <reified T> Router.getRoute(): T {
    return savedStateHandle.get<T>(T::class.java.name) as T
}
