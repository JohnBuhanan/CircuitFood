package com.johnbuhanan.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

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

inline fun <reified T> Router.setResult(result: T) {
    return savedStateHandle.set(T::class.java.name, result)
}

inline fun <reified T> Router.listenForResult(
    viewModel: ViewModel,
    crossinline action: (T) -> Unit
) {
    viewModel.viewModelScope.launch {
        savedStateHandle.getLiveData<T>(T::class.java.name).asFlow().collect {
            action(it)
        }
    }
}

fun <T> LiveData<T>.asFlow(): Flow<T> = callbackFlow {
    val observer = Observer<T> { value -> trySend(value) }
    observeForever(observer)
    awaitClose {
        removeObserver(observer)
    }
}.flowOn(Dispatchers.Main.immediate)