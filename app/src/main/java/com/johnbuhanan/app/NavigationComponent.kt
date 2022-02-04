package com.johnbuhanan.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.johnbuhanan.app.destinations.StartDestination
import com.johnbuhanan.common.FeatureNavGraphMap
import com.johnbuhanan.common.FeatureRoute
import com.johnbuhanan.common.Router
import com.johnbuhanan.common.RouterEvent.GoBack
import com.johnbuhanan.common.RouterEvent.GoToFeature
import com.johnbuhanan.common.RouterEvent.GoToScreen
import com.johnbuhanan.common.RouterEvent.SynthesizeBackStack
import com.johnbuhanan.common.ScreenRoute
import com.johnbuhanan.common.toGeneric
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@Composable
fun NavigationComponent(router: Router, featureNavGraphMap: FeatureNavGraphMap) {
    val appGraph = NavGraphs.root.toGeneric().copy(nestedNavGraphs = featureNavGraphMap.values.map { it.get() })
    val engine = rememberNavHostEngine()
    val navController = engine.rememberNavController()

    fun FeatureRoute.getRoute(): String {
        val clazz = this::class.java
        return featureNavGraphMap[clazz]?.get()?.route ?: ""
    }

    LaunchedEffect(navController) {
        router.routerEvents.collect {
            Timber.e("ROUTER")
            when (val event = it) {
                is GoBack -> navController.navigateUp()
                is GoToFeature -> {
                    navController.navigate(
                        route = event.featureRoute.getRoute(),
                        builder = event.builder,
                    )
                }
                is GoToScreen -> {
                    navController.navigate(
                        route = event.screenRoute.route,
                        builder = event.builder,
                    )
                }
                is SynthesizeBackStack -> {
                    val routes: List<String> = event.routes.map { eventRoute ->
                        when (eventRoute) {
                            is FeatureRoute -> eventRoute.getRoute()
                            is ScreenRoute -> eventRoute.route
                            else -> eventRoute as String
                        }
                    }

                    navController.synthesizeBackStack(routes)
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

fun NavController.synthesizeBackStack(routes: List<String>) {
    routes.reversed().forEach { navigate(it) }
}
