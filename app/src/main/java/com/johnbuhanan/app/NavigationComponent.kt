package com.johnbuhanan.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.johnbuhanan.app.destinations.StartDestination
import com.johnbuhanan.common.DirectionMap
import com.johnbuhanan.common.NavGraphMap
import com.johnbuhanan.common.RouterEvent
import com.johnbuhanan.common.RouterViewModel
import com.johnbuhanan.common.toGeneric
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@Composable
fun NavigationComponent(navGraphMap: NavGraphMap, directionMap: DirectionMap) {
    val routerViewModel = hiltViewModel<RouterViewModel>()
    val appGraph = NavGraphs.root.toGeneric().copy(nestedNavGraphs = navGraphMap.values.map { it.get() })
    val engine = rememberNavHostEngine()
    val navController = engine.rememberNavController()

    LaunchedEffect(navController) {
        routerViewModel.routerEvents.collect {
            Timber.e("ROUTER")
            when (val event = it) {
                is RouterEvent.GoBack -> navController.navigateUp()
                is RouterEvent.GoTo -> {
                    val eventRoute = event.route
                    val clazz = eventRoute::class.java
                    val navGraphRoute = navGraphMap[clazz]?.get()?.route
                    val destinationRoute = directionMap[clazz]?.invoke(eventRoute)?.route
                    val route = navGraphRoute ?: destinationRoute ?: ""

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
