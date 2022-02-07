package com.johnbuhanan.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.concurrent.ThreadSafeMap
import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.johnbuhanan.navigation.Route
import com.johnbuhanan.navigation.Router
import com.johnbuhanan.navigation.RouterEvent.Pop
import com.johnbuhanan.navigation.RouterEvent.Push
import timber.log.Timber
import kotlin.reflect.KClass

var getNavigator: (() -> Navigator)? = null
private typealias ProviderKey = KClass<out ScreenProvider>
private typealias ScreenFactory = (ScreenProvider) -> Screen

@Composable
fun NavigationComponent(router: Router) {
    LaunchedEffect(Unit) {
        Timber.e("BEFORE ROUTER")
        router.routerEvents.collect { routerEvent ->
            Timber.e("routerEvent: $routerEvent")
            when (routerEvent) {
                is Pop -> {
                    Timber.e("GoBack")
                    getNavigator!!().pop()
                }
                is Push -> {
                    val screens = routerEvent.routes.map { it.toScreen() }
                    getNavigator!!().push(screens)
                }
            }
        }
    }

    Navigator(StartScreen())
}

@Suppress("UNCHECKED_CAST")
object ScreenRegistryHelper {
    private val factoriesMember by lazy { ScreenRegistry::class.members.find { it.name == "factories" } }
    private val factories: ThreadSafeMap<ProviderKey, ScreenFactory> by lazy {
        factoriesMember!!.call(ScreenRegistry) as ThreadSafeMap<ProviderKey, ScreenFactory>
    }

    fun get(route: Route): Screen {
        val screenFactory = factories[route::class]!!
        return screenFactory.invoke(route)
    }
}

fun Route.toScreen(): Screen {
    return ScreenRegistryHelper.get(this)
}