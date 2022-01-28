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
import com.ramcosta.composedestinations.spec.Direction
import com.ramcosta.composedestinations.spec.NavGraphSpec
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import kotlin.reflect.KClass

typealias DirectionTransform = (Route) -> Direction
typealias NavGraphMap = Map<KClass<out Route>, @JvmSuppressWildcards NavGraphSpec>
typealias DirectionMap = Map<KClass<out Route>, @JvmSuppressWildcards DirectionTransform>

// Single Activity per app
@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {
//    @Inject
//    lateinit var navGraphMap: NavGraphMap


    private val destinationTransform: DirectionTransform = {
        val id = (it as Route.FoodCategoryDetails).id
        FoodCategoryDetailsDestination(id)
    }

    private val navGraphMap: NavGraphMap = mapOf(
        Route.FoodCategories::class to com.codingtroops.NavGraphs.root
    )

    private val destinationMap: DirectionMap = mapOf(
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
                Initialize(navGraphMap, destinationMap)
            }
        }
    }
}

@Composable
fun Initialize(
    navGraphMap: NavGraphMap,
    destinationMap: DirectionMap,
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
                    val eventRoute = event.route
                    val clazz = eventRoute::class
                    val navGraphRoute = navGraphMap[clazz]?.route
                    val destinationRoute = destinationMap[clazz]?.invoke(eventRoute)?.route
                    val route = navGraphRoute ?: destinationRoute ?: ""

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
