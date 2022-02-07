package com.johnbuhanan.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.concurrent.ThreadSafeMap
import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.johnbuhanan.navigation.Router
import com.johnbuhanan.navigation.RouterEvent.GoBack
import com.johnbuhanan.navigation.RouterEvent.GoTo
import timber.log.Timber
import kotlin.reflect.KClass

var getNavigator: (() -> Navigator)? = null
private typealias ProviderKey = KClass<out ScreenProvider>
private typealias ScreenFactory = (ScreenProvider) -> Screen

@Composable
fun NavigationComponent(router: Router) {
    val member = ScreenRegistry::class.members.find { it.name == "factories" }
    val factories: ThreadSafeMap<ProviderKey, ScreenFactory> = member!!.call(ScreenRegistry) as ThreadSafeMap<ProviderKey, ScreenFactory>

    LaunchedEffect(Unit) {
        Timber.e("BEFORE ROUTER")
        router.routerEvents.collect {
            Timber.e("routerEvent: $it")
            when (val event = it) {
                is GoBack -> {
                    Timber.e("GoBack")
                }//navigator.pop()
                is GoTo -> {
                    val eventRoute = event.route
                    val navigator: Navigator = getNavigator?.invoke()!!
                    val screenFactory = factories[eventRoute::class]
                    val screen = screenFactory?.invoke(eventRoute)!!

                    navigator.push(screen)
                }
            }
        }
    }

    Navigator(StartScreen())
}
