package com.johnbuhanan.common

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

interface Router {
    fun goBack(): Boolean
    fun goToFeature(featureRoute: FeatureRoute, builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true }): Boolean
    fun goToScreen(screenRoute: ScreenRoute, builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true }): Boolean
    fun synthesizeBackStack(routes: List<*>, builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true }): Boolean
    val routerEvents: Flow<RouterEvent>
}

@Singleton
class RouterImpl @Inject constructor() : Router {
    private val _routerEvents = Channel<RouterEvent>()
    override val routerEvents = _routerEvents.receiveAsFlow()

    override fun goBack(): Boolean = _routerEvents.trySend(RouterEvent.GoBack).isSuccess
    override fun goToFeature(featureRoute: FeatureRoute, builder: NavOptionsBuilder.() -> Unit): Boolean =
        _routerEvents.trySend(RouterEvent.GoToFeature(featureRoute, builder)).isSuccess

    override fun goToScreen(screenRoute: ScreenRoute, builder: NavOptionsBuilder.() -> Unit): Boolean =
        _routerEvents.trySend(RouterEvent.GoToScreen(screenRoute, builder)).isSuccess

    override fun synthesizeBackStack(routes: List<*>, builder: NavOptionsBuilder.() -> Unit): Boolean =
        _routerEvents.trySend(RouterEvent.SynthesizeBackStack(routes, builder)).isSuccess
}
