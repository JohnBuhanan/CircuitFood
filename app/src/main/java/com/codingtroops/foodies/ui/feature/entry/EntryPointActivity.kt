package com.codingtroops.foodies.ui.feature.entry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle.State.RESUMED
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import com.codingtroops.common.AuroraNavigatorViewModel
import com.codingtroops.common.GenericNavGraph
import com.codingtroops.common.NavigatorEvent
import com.codingtroops.common.Route
import com.codingtroops.destinations.FoodCategoryDetailsDestination
import com.codingtroops.foodies.ui.feature.entry.destinations.StartDestination
import com.codingtroops.foodies.ui.theme.ComposeSampleTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavController
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import kotlin.reflect.KClass

typealias DestinationTransform<T> = (T) -> DestinationSpec<*>
//typealias NavGraphTransform = (Route) -> NavGraphSpec

typealias NavGraphMap = Map<Route, @JvmSuppressWildcards NavGraphSpec>
typealias DestinationMap = Map<KClass<out Route>, @JvmSuppressWildcards DestinationTransform<*>>

//fun <T : Route> doThing(): DestinationSpec<*> {
//
//}

// Single Activity per app
@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {
//    @Inject
//    lateinit var navGraphs: Map<Class<out Routes>, @JvmSuppressWildcards NavGraphSpec>

    private val destinationTransform: DestinationTransform<Route.FoodCategoryDetails> = {
        FoodCategoryDetailsDestination(it.id) as DestinationSpec<*>
    }
//    private val navGraphTransform: NavGraphTransform = {
//
//    }

    private val nestedNavGraphs: NavGraphMap = mapOf(
        Route.FoodCategories to com.codingtroops.NavGraphs.root
    )

    private val navDestinations = mapOf<KClass<out Route>, DestinationTransform<*>>(
        Route.FoodCategoryDetails::class to destinationTransform
    )

    // When two features can't see eachother, how do you navigate from one to the other?
    // We need to map shared "Route" objects to them.
    // NavGraphs

//    private val navDestinations = mapOf<KClass<out Route>, DestinationSpec<*>>(
//        Route.FoodCategoryDetails::class to FoodCategoryDetailsDestination
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeSampleTheme {
                Initialize(nestedNavGraphs, navDestinations)
            }
        }
    }
}

@Composable
fun Initialize(
    navGraphMap: NavGraphMap,
    destinationMap: DestinationMap,
) {
    val engine = rememberNavHostEngine()
    val navController = engine.rememberNavController()

    val navBackStackEntry = NavBackStackEntry.create(
        context = LocalContext.current,
        destination = NavDestination(""),
        hostLifecycleState = RESUMED
    )
    val destinationsNavController = DestinationsNavController(
        navController = navController,
        navBackStackEntry = navBackStackEntry,
    )
    val auroraNavigatorViewModel = hiltViewModel<AuroraNavigatorViewModel>()

    LaunchedEffect(Unit) {
        auroraNavigatorViewModel.destinations.collect {
            Timber.e("HERE!!!")
            when (val event = it) {
                is NavigatorEvent.NavigateUp -> destinationsNavController.navigateUp()
                is NavigatorEvent.Directions -> {
//                    val destinationTransform = destinationMap[event.route::class] as DestinationTransform<Route.FoodCategoryDetails>
                    val route = when (event.route) {
                        is Route.FoodCategories -> navGraphMap[event.route]!!.route
                        is Route.FoodCategoryDetails -> {
                            val newRoute = event.route as Route.FoodCategoryDetails
                            FoodCategoryDetailsDestination(newRoute.id).route
                        }
                        else -> {
                            ""
                        }
                    }


//                    val navGraph = navGraphMap[event.route]// .invoke(event.route)// ?: navDestinations[event.route::class]

                    destinationsNavController.navigate(
                        route = route,
                        onlyIfResumed = false,
                        builder = event.builder,
                    )
                }
            }
        }
    }

    val appGraph = GenericNavGraph(
        route = "app",
        startDestination = StartDestination,
        destinations = listOf(
            StartDestination
        ),
        nestedNavGraphs = navGraphMap.values.toList()
    )

    DestinationsNavHost(
        navGraph = appGraph,
        engine = engine,
        navController = navController,
    )
}

@Destination(start = true)
@Composable
fun Start(navigator: DestinationsNavigator) {
//    val thing = navGraphs[Routes.FoodCategories] as Direction
    navigator.navigate(com.codingtroops.NavGraphs.root)
}
