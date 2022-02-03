package com.johnbuhanan.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.johnbuhanan.app.destinations.StartDestination
import com.johnbuhanan.common.NavGraphMap
import com.johnbuhanan.common.Router
import com.johnbuhanan.common.RouterEvent.GoBack
import com.johnbuhanan.common.RouterEvent.GoToFeature
import com.johnbuhanan.common.RouterEvent.GoToScreen
import com.johnbuhanan.common.toGeneric
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@Composable
fun NavigationComponent(router: Router, navGraphMap: NavGraphMap) {
    val appGraph = NavGraphs.root.toGeneric().copy(nestedNavGraphs = navGraphMap.values.map { it.get() })
    val engine = rememberNavHostEngine()
    val navController = engine.rememberNavController()

    LaunchedEffect(navController) {
        router.routerEvents.collect {
            Timber.e("ROUTER")
            when (val event = it) {
                is GoBack -> navController.navigateUp()
                is GoToFeature -> {
                    val featureRoute = event.featureRoute
                    val clazz = featureRoute::class.java
                    val route = navGraphMap[clazz]?.get()?.route ?: ""

                    navController.navigate(
                        route = route,
                        builder = event.builder,
                    )
                }
                is GoToScreen -> {
                    val route = event.screenRoute.route

                    navController.navigate(
                        route = route,
                        builder = event.builder,
                    )
                }
            }
        }
    }

    DestinationsNavHost(
        navGraph = appGraph,
        startDestination = StartDestination,
        engine = engine,
        navController = navController,
    )
}
