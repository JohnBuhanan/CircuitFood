package com.johnbuhanan.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.johnbuhanan.features.food.Food
import com.johnbuhanan.navigation.Route
import com.johnbuhanan.navigation.Router
import com.johnbuhanan.navigation.RouterEvent.GoBack
import com.johnbuhanan.navigation.RouterEvent.GoTo
import timber.log.Timber

var getNavigator: (() -> Navigator)? = null
var getScreen: ((Route) -> Screen)? = null

@Composable
fun NavigationComponent(router: Router) {
    val screen = rememberScreen(Food.Route.FoodCategoryDetails("1"))
    LaunchedEffect(Unit) {
        Timber.e("BEFORE ROUTER")
        router.routerEvents.collect {
            Timber.e("routerEvent: $it")
            when (val event = it) {
                is GoBack -> {
                    Timber.e("GoBack")
                }//navigator.pop()
                is GoTo<*> -> {
                    Timber.e("GoTo: ${event.route}")
//                    val screen = ScreenRegistry.get(event.route)
                    getNavigator?.invoke()?.push(screen)
                }
            }
        }
    }

    Navigator(StartScreen())
}
